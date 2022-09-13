package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class DetailTargetResponse(

    @field:SerializedName("data")
    val data: DataItem? = null,

    @field:SerializedName("meta")
    val meta: MetaDetailTarget? = null
)

data class UserTargetsItem(

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("target_id")
    val targetId: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,
)

data class MetaDetailTarget(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("messages")
    val messages: String? = null
)

data class DataItem(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("user_targets")
    val userTargets: ArrayList<UserTargetsItem>? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("image_url")
    val imageUrl: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("remaining")
    val remaining: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("count_day")
    val count_day: Int? = 0,


)
