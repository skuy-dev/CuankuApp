package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class DailyCatatanResponse(

//	@field:SerializedName("data")
//	val data: DataDaily? = null,

	@field:SerializedName("data")
	val data: Map<String, ArrayList<DataDailyItem>>,
	val meta: Meta? = null
)

data class DataItemCombined(
	val date: String? = null,
	val data: ArrayList<DataDailyItem>
)

data class DataDailyItem(
	val id: Int? = null,
	val saving_id: Int? = null,
	val nominal: Int? = null,
	val type: String? = null,
	val date: String? = null,
	val savings: SavingsItem? = null,
)


data class SavingsItem(
	val id: Int? = null,
	val userId: Int? = null,
	val name: String? = null,
	val image: String? = null,
	val total: Int? = null,
	val updated_at: String? = null,
	val created_at: String? = null,
)

