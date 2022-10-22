package com.example.cuanku.source

import com.example.cuanku.request.AddCatatanRequest
import com.example.cuanku.request.AddKategoriBookRequest
import com.example.cuanku.service.BookServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookDataSource @Inject constructor(private val service: BookServiceInstance) {

    suspend fun addCatatan(request: AddCatatanRequest) = service.AddCatatan(request)

    suspend fun getDailyCatatan() = service.getDailyCatatan()

    suspend fun addKategori(request: AddKategoriBookRequest) = service.addKategori(request)

    suspend fun getListKategori() = service.getListKategori()

}