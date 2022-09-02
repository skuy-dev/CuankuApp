package com.example.cuanku.source

import com.example.cuanku.service.TargetsServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TargetsDataSource @Inject constructor(private val service: TargetsServiceInstance) {

    suspend fun getListTargets(token: String) = service.getListTargets(token)

}