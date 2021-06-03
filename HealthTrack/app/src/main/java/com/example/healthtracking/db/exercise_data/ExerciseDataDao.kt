package com.example.healthtracking.db.exercise_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDataDao {

    @Insert
    suspend fun insert(exerciseData: ExerciseDataModel)

    @Query("SELECT * FROM exercise_data")
    fun getAllData(): LiveData<List<ExerciseDataModel>>

    @Query("DELETE FROM exercise_data")
    suspend fun deleteAll()

    @Query("SELECT * FROM exercise_data WHERE type LIKE :word")
    fun getSpecificValue(word: String): LiveData<ExerciseDataModel>
}