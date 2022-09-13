package com.example.cuanku.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class DataListTargets(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @SerializedName("image_url")
    val image_url: String? = null,

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
) : Parcelable
