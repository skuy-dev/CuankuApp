package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class ListDoneTargetResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItemDone>? = null,

	@field:SerializedName("meta")
	val meta: MetaDone? = null
)

data class DataItemDone(

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("nominal")
	val nominal: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("remaining")
	val remaining: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class MetaDone(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("messages")
	val messages: String? = null
)
