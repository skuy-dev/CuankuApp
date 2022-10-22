package com.example.cuanku.response

import com.google.gson.annotations.SerializedName

data class AddCatatanResponse(

    @field:SerializedName("data")
    val data: DataAddCatatan? = null,

    @field:SerializedName("meta")
    val meta: Meta? = null
)

data class DataAddCatatan(

    @field:SerializedName("nominal")
    val nominal: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("saving_id")
    val savingId: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: String? = null
)
