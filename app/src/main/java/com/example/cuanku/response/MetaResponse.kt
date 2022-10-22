package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class MetaResponse(

    @field:SerializedName("meta")
    val meta: Meta? = null
)

data class Meta(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("messages")
    val messages: String? = null
)
