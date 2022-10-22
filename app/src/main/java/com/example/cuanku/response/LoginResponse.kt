package com.example.cuanku.response

data class LoginResponse(
    val meta: Meta? = null,
    val data: LoginData? = null
)

data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val email_verified_at: Any? = null,
    val phone_number: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
)

data class LoginData(
    val user: User? = null,
    val token: String? = null
)
