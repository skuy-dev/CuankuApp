package com.example.cuanku.service

import com.example.cuanku.request.LoginRequest
import com.example.cuanku.request.RegisterRequest
import com.example.cuanku.response.LoginResponse
import com.example.cuanku.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceInstance {

    @POST("auth/login")
    suspend fun postLogin(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("auth/register")
    suspend fun postRegister(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

}