package com.example.healthtracking.db.authentication

data class UserCreateModel(
    val username : String,
    val email : String,
    val password : String
)
