package com.example.cuanku.source

import com.example.cuanku.request.LoginRequest
import com.example.cuanku.request.RegisterRequest
import com.example.cuanku.service.AuthServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataSource @Inject constructor(
    private val service: AuthServiceInstance
) {

    suspend fun postLogin(request: LoginRequest) = service.postLogin(request)

    suspend fun postRegister(request: RegisterRequest) = service.postRegister(request)

}