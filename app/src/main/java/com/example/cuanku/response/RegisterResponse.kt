package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("data")
    val data: RegisterData? = null,

    @field:SerializedName("meta")
    val meta: RegisterMeta? = null
)

data class RegisterData(

    @field:SerializedName("user")
    val user: RegisterUser? = null,

    @field:SerializedName("token")
    val token: String? = null
)

data class RegisterMeta(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("messages")
    val messages: String? = null
)

data class RegisterUser(

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("phone_number")
    val phoneNumber: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null
)
