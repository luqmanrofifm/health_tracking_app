package com.example.healthtracking.db.user_health

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthtracking.db.Converter
import com.example.healthtracking.db.exercise_data.ExerciseDataDB
import com.example.healthtracking.db.exercise_data.ExerciseDataDao
import com.example.healthtracking.db.exercise_data.ExerciseDataModel

@Database(entities = [UserHealthModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class UserHealthDB:RoomDatabase() {
    abstract val userHealthDao: UserHealthDao

    companion object {
        @Volatile
        private var INSTANCE: UserHealthDB? = null

        fun getInstance(context: Context): UserHealthDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserHealthDB::class.java,
                        "user_health"
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