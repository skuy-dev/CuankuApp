package com.example.cuanku.response

data class KategoriBookResponse(
	val data: KategoriBookItem? = null,
	val meta: Meta? = null
)

data class KategoriBookItem(
	val user_id: Int? = null,
	val name: String? = null,
	val image: String? = null,
	val updated_at: String? = null,
	val created_at: String? = null,
	val id: Int? = null
)
