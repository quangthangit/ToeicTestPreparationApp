package com.example.toeicpreparation.data.remote

data class User(
    val username: String,
    val role: String,
    val isActive: Boolean,
    val token: String,
)