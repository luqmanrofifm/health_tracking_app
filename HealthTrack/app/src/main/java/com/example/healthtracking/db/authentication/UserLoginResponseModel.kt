package com.example.healthtracking.db.authentication

data class UserLoginResponseModel (
    val status : Boolean,
    val error : String,
    val user : List<User>
)

