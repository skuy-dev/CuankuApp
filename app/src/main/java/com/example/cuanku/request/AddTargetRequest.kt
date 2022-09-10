package com.example.cuanku.request

import java.io.File

data class AddTargetRequest(
    val name: String? = null,
    val duration: String? = null,
    val remaining: String? = null,
    val image_url: File? = null,
    val nominal: Int? = 0
)