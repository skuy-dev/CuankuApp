package com.example.cuanku.service

import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.response.AddTabunganTargetResponse
import com.example.cuanku.response.AddTargetResponse
import com.example.cuanku.response.DetailTargetResponse
import com.example.cuanku.response.ListTargetsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface TargetsServiceInstance {

    @GET("targets")
    suspend fun getListTargets(
    ): Response<ListTargetsResponse>

//    @POST("targets")
//    suspend fun addTargets(
//        @Body request: AddTargetRequest
//    ): Response<AddTargetResponse>

//    @Multipart
//    @POST("targets")
//    suspend fun addTargets(
//        @Part("name") name: MultipartBody.Part,
//        @Part("duration") duration: MultipartBody.Part,
//        @Part("remaining") remaining: MultipartBody.Part,
//        @Part image_url: MultipartBody.Part,
//        @Part("nominal") nominal: MultipartBody.Part
//    ): Response<AddTargetResponse>

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

}