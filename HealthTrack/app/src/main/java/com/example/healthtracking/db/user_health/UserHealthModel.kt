package com.example.healthtracking.db.user_health

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "user_health")
data class UserHealthModel(

    @PrimaryKey
    @ColumnInfo(name = "date_time")
    var dateTime: Date?,

    @ColumnInfo(name = "user_id")
    var userId: Int?,

    @ColumnInfo(name = "weight")
    var weight: Int?,

    @ColumnInfo(name = "height")
    var height: Int?,

    @ColumnInfo(name = "pulse")
    var pulse: Int?
)