package com.example.cuanku.service

import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface TargetsServiceInstance {

    @GET("targets")
    suspend fun getListTargets(
    ): Response<ListTargetsResponse>

    @Multipart
    @POST("targets")
    suspend fun addTargets(
        @Part image_url: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("duration") duration: RequestBody,
        @Part("remaining") remaining: RequestBody,
        @Part("nominal") nominal: RequestBody
    ): Response<AddTargetResponse>

    @GET("targets/{id}")
    suspend fun getDetailTarget(
        @Path("id") id: Int?
    ): Response<DetailTargetResponse>

    @POST("user-targets")
    suspend fun addTabunganTarget(
        @Body request: AddTabunganTargetRequest
    ): Response<AddTabunganTargetResponse>

    @GET("target/done")
    suspend fun getDoneTarget(
    ): Response<ListDoneTargetResponse>

    @PUT("targets/{id}")
    suspend fun toDoneTarget(
        @Path("id") id: Int?
    ): Response<ToDoneTargetResponse>

    @DELETE("targets/{id}")
    suspend fun deleteTarget(
        @Path("id") id: Int?
    ): Response<MetaResponse>

}