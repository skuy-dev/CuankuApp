package com.example.cuanku.service

import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.response.AddTargetResponse
import com.example.cuanku.response.ListTargetsResponse
import retrofit2.Response
import retrofit2.http.*

interface TargetsServiceInstance {

    @GET("targets")
    suspend fun getListTargets(
    ): Response<ListTargetsResponse>

    @POST("targets")
    suspend fun addTargets(
        @Body request: AddTargetRequest
    ): Response<AddTargetResponse>

}