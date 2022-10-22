package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class ListKategoriBookResponse(
	val data: ArrayList<ListKategoriItem>? = null,
	val meta: Meta? = null
)

data class ListKategoriItem(
	val id: Int? = null,
	val user_id: Int? = null,
	val name: String? = null,
	val image: String? = null,
	val total: Int? = null,
	val created_at: String? = null,
	val updated_at: String? = null
)
