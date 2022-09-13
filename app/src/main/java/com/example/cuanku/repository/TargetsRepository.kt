package com.example.cuanku.repository

import com.example.cuanku.base.BaseApiResponse
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.response.*
import com.example.cuanku.source.TargetsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    suspend fun addTargets(
        file: MultipartBody.Part,
        name: RequestBody,
        duration: RequestBody,
        remaining: RequestBody,
        nominal: RequestBody
    ): Flow<NetworkResult<AddTargetResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.addTargets(file, name, duration, remaining, nominal)
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

    suspend fun addTabunganTarget(request: AddTabunganTargetRequest): Flow<NetworkResult<AddTabunganTargetResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.addTabunganTarget(request)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDoneTarget(): Flow<NetworkResult<ListDoneTargetResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getDoneTarget()
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun toDoneTarget(id: Int?): Flow<NetworkResult<ToDoneTargetResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.toDoneTarget(id)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteTarget(id: Int?): Flow<NetworkResult<MetaResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.deleteTarget(id)
            })
        }
    }

}