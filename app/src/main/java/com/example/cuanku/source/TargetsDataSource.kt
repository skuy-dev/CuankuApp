package com.example.cuanku.source

import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.service.TargetsServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TargetsDataSource @Inject constructor(private val service: TargetsServiceInstance) {

    suspend fun getListTargets() = service.getListTargets()

    suspend fun addTargets(request: AddTargetRequest) = service.addTargets(request)

    suspend fun getDetailTarget(id: Int?) = service.getDetailTarget(id)

}