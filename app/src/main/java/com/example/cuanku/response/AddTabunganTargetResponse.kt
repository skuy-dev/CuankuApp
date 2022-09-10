package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class AddTabunganTargetResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("meta")
	val meta: MetaTabunganTarget? = null
)

data class Data(

	@field:SerializedName("nominal")
	val nominal: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("target_id")
	val targetId: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class MetaTabunganTarget(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("messages")
	val messages: String? = null
)
