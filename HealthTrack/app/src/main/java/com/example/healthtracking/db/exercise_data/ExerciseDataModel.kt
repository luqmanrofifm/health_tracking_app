package com.example.healthtracking.db.exercise_data

import androidx.room.*
import java.util.*

@Entity(tableName = "exercise_data")
data class ExerciseDataModel(
    @PrimaryKey
    @ColumnInfo(name = "time_add")
    var timeAdd: Date?,

    @ColumnInfo(name = "user_id")
    var userId: Int?,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "time_end")
    var timeEnd: Date?,

    @ColumnInfo(name = "distance")
    var distance: Int?,

    @ColumnInfo(name = "sets")
    var sets: Int?,

    @ColumnInfo(name = "calories")
    var calories: Int?
)
