package com.example.healthtracking.db.exercise_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthtracking.db.Converter

@Database(entities = [ExerciseDataModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ExerciseDataDB: RoomDatabase() {
    abstract val exerciseDataDao: ExerciseDataDao

    companion object {
        @Volatile
        private var INSTANCE: ExerciseDataDB? = null

        fun getInstance(context: Context): ExerciseDataDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExerciseDataDB::class.java,
                        "exercise_data"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}