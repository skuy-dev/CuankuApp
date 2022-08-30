package com.example.cuanku.repository

import com.example.cuanku.base.BaseApiResponse
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.request.LoginRequest
import com.example.cuanku.request.RegisterRequest
import com.example.cuanku.response.LoginResponse
import com.example.cuanku.response.RegisterResponse
import com.example.cuanku.source.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val dataSource: AuthDataSource
) : BaseApiResponse() {

    suspend fun postLogin(request: LoginRequest): Flow<NetworkResult<LoginResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.postLogin(request)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postRegister(request: RegisterRequest): Flow<NetworkResult<RegisterResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.postRegister(request)
            })
        }.flowOn(Dispatchers.IO)
    }

}