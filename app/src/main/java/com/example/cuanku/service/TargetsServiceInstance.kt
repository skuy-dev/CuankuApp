package com.example.cuanku.service

import com.example.cuanku.response.ListTargetsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TargetsServiceInstance {

    @GET("targets")
    suspend fun getListTargets(
        @Header("Authorization") authHeader: String): Response<ListTargetsResponse>

}