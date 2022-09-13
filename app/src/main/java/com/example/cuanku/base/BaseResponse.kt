package com.example.cuanku.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val data: T? = null,

    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("message")
    val message: String? = null,
)

data class Meta(
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("messages")
    val messages: String? = null
)