package com.example.cuanku.response

data class AddTargetResponse(
	val meta: Meta? = null,
	val data: AddTargetItem? = null
)

data class AddTargetItem(
	val user_id: Int? = null,
	val name: String? = null,
	val nominal: Int? = null,
	val duration: String? = null,
	val remaining: Int? = null,
	val id: Int? = null,
	val updated_at: String? = null,
	val created_at: String? = null
)
