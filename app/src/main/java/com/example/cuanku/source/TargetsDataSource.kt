package com.example.cuanku.source

import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.service.TargetsServiceInstance
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Part
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TargetsDataSource @Inject constructor(private val service: TargetsServiceInstance) {

    suspend fun getListTargets() = service.getListTargets()

//    suspend fun addTargets(request: AddTargetRequest) = service.addTargets(request)

    suspend fun addTargets(
        file: MultipartBody.Part,
        name: RequestBody,
        duration: RequestBody,
        remaining: RequestBody,
        nominal: RequestBody
    ) = service.addTargets(file, name, duration, remaining, nominal)

//    suspend fun addTargets(
//        name: MultipartBody.Part,
//        duration: MultipartBody.Part,
//        remaining: MultipartBody.Part,
//        image: MultipartBody.Part,
//        nominal: MultipartBody.Part
//    ) = service.addTargets(
//        name,
//        duration,
//        remaining,
//        image,
//        nominal
//    )

    suspend fun getDetailTarget(id: Int?) = service.getDetailTarget(id)

    suspend fun addTabunganTarget(request: AddTabunganTargetRequest) =
        service.addTabunganTarget(request)

}