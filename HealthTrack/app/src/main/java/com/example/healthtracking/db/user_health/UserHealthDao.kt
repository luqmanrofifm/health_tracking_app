package com.example.healthtracking.db.user_health

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthtracking.db.exercise_data.ExerciseDataModel

@Dao
interface UserHealthDao {

    @Insert
    suspend fun insert(userHealth: UserHealthModel)

    @Query("SELECT * FROM user_health")
    fun getAllData(): LiveData<List<UserHealthModel>>

}