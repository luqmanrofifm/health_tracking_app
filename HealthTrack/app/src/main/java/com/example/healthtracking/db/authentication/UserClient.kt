package com.example.healthtracking.db.authentication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//moved to Koin
class UserClient {
    val baseurl = ""

    val instance : UserApi by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitBuilder.create(UserApi::class.java)
    }

}