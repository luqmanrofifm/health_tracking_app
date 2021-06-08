package com.example.healthtracking.db.authentication

import androidx.room.ColumnInfo
import com.example.healthtracking.db.user_health.UserHealthModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

interface UserApi {

    @FormUrlEncoded
    @POST("/signUp")
    fun signUp(
        @Field("username")username : String,
        @Field("email")email : String,
        @Field("password")password : String
    ) : Call<UserCreateModel>

    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("email")email: String,
        @Field("password")password: String
    ) : Call<UserLoginModel>

    @FormUrlEncoded
    @POST("/") //TODO("Masih belum ada nilainya")
    fun updateUserData(
        @Field("dateTime")datetime : Date,
        @Field("userId")userId : Int,
        @Field("weight")weight : Int,
        @Field("height")height : Int,
        @Field("sleep")sleep : Int,
        @Field("pulse")pulse : Int,
        @Field("oxygen")oxygen : Int
    ) : Call<UserHealthModel>

    @FormUrlEncoded
    @POST("/") //TODO("Masih belum ada nilainya")
    fun uploadExerciseData(
        @Field("time_Add")timeAdd : Date,
        @Field("user_Id")userId : Int,
        @Field("type")type : String,
        @Field("time_End")timeEnd: Date,
        @Field("distance")distance : Int,
        @Field("sets")sets : Int,
        @Field("calories")calories : Int
    )

}