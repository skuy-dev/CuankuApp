package com.example.cuanku.response

data class DetailTargetResponse(
    val meta: Meta? = null,
    val data: DetailTargetItem? = null
)

data class DetailTargetItem(
    val id: Int? = null,
    val user_id: Int? = null,
    val name: String? = null,
    val nominal: Int? = null,
    val duration: String? = null,
    val remaining: Int? = null,
    val image_url: Any? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val status: Int? = null,
    val count_day: Int? = 0,
    val user_targets: ArrayList<UserTargetsItem>? = null
)

data class UserTargetsItem(
    val id: Int? = null,
    val target_id: Int? = null,
    val nominal: Int? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)
