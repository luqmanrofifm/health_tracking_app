package com.example.healthtracking.db.authentication

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
}