package com.example.cuanku.repository

import com.example.cuanku.base.BaseApiResponse
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.response.AddTargetResponse
import com.example.cuanku.response.DetailTargetResponse
import com.example.cuanku.response.ListTargetsResponse
import com.example.cuanku.source.TargetsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TargetsRepository @Inject constructor(private val dataSource: TargetsDataSource) :
    BaseApiResponse() {
    suspend fun getListTargets(): Flow<NetworkResult<ListTargetsResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getListTargets()
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addTargets(request: AddTargetRequest): Flow<NetworkResult<AddTargetResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.addTargets(request)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTarget(id: Int?): Flow<NetworkResult<DetailTargetResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getDetailTarget(id)
            })
        }.flowOn(Dispatchers.IO)
    }

}