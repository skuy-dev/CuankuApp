package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class ListTargetsResponse(

    @field:SerializedName("data")
    val data: ArrayList<DataListTargets>? = null,

    @field:SerializedName("meta")
    val meta: MetaListTarget? = null
)

data class MetaListTarget(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("messages")
    val messages: String? = null
)

data class DataListTargets(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("remaining")
    val remaining: Int? = null
)
