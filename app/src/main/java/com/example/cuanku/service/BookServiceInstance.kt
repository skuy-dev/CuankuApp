package com.example.cuanku.service

import com.example.cuanku.request.AddCatatanRequest
import com.example.cuanku.request.AddKategoriBookRequest
import com.example.cuanku.response.AddCatatanResponse
import com.example.cuanku.response.DailyCatatanResponse
import com.example.cuanku.response.KategoriBookResponse
import com.example.cuanku.response.ListKategoriBookResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookServiceInstance {

    @POST("user-savings")
    suspend fun AddCatatan(
        @Body request: AddCatatanRequest
    ): Response<AddCatatanResponse>

    @GET("daily")
    suspend fun getDailyCatatan(
    ): Response<DailyCatatanResponse>

    @POST("savings")
    suspend fun addKategori(
        @Body request: AddKategoriBookRequest
    ): Response<KategoriBookResponse>

    @GET("savings")
    suspend fun getListKategori(
    ): Response<ListKategoriBookResponse>

}