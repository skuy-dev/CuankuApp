package com.example.cuanku.request

data class AddTargetRequest(
    val name: String? = null,
    val duration: String? = null,
    val images_url: String? = null,
    val remaining: Int? = 0,
    val nominal: Int? = 0
)