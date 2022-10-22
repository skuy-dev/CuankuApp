package com.example.cuanku.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ListTargetsResponse(
    val meta: Meta? = null,
    val data: ArrayList<ListTargetItem>? = null
)

@Parcelize
data class ListTargetItem(
    val id: Int? = null,
    val user_id: Int? = null,
    val name: String? = null,
    val nominal: Int? = null,
    val duration: String? = null,
    val remaining: Int? = null,
    val image_url: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val status: Int? = null,
    val count_day: Int? = 0
) : Parcelable
