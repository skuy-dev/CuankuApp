package com.example.cuanku.response

data class ToDoneTargetResponse(
	val meta: Meta? = null,
	val data: DoneTargetItem? = null,
)

data class DoneTargetItem(
	val id: Int? = null,
	val user_id: Int? = null,
	val name: String? = null,
	val nominal: Int? = null,
	val duration: String? = null,
	val remaining: Int? = null,
	val image_url: String? = null,
	val created_at: String? = null,
	val updated_at: String? = null,
	val status: Int? = null
)
