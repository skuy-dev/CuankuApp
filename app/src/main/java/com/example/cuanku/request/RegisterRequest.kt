package com.example.cuanku.request

data class RegisterRequest(
    val name: String? = null,
    val email: String? = null,
    val phone_number: String? = null,
    val password: String? = null
)