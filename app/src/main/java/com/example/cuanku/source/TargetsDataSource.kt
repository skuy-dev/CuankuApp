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

    suspend fun addTargets(
        file: MultipartBody.Part,
        name: RequestBody,
        duration: RequestBody,
        remaining: RequestBody,
        nominal: RequestBody
    ) = service.addTargets(file, name, duration, remaining, nominal)

    suspend fun getDetailTarget(id: Int?) = service.getDetailTarget(id)

    suspend fun addTabunganTarget(request: AddTabunganTargetRequest) =
        service.addTabunganTarget(request)

    suspend fun getDoneTarget() = service.getDoneTarget()

    suspend fun toDoneTarget(id: Int?) = service.toDoneTarget(id)

    suspend fun deleteTarget(id: Int?) = service.deleteTarget(id)

}