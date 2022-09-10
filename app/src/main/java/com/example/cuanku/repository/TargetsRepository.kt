package com.example.cuanku.repository

import com.example.cuanku.base.BaseApiResponse
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.request.AddTabunganTargetRequest
import com.example.cuanku.request.AddTargetRequest
import com.example.cuanku.response.AddTabunganTargetResponse
import com.example.cuanku.response.AddTargetResponse
import com.example.cuanku.response.DetailTargetResponse
import com.example.cuanku.response.ListTargetsResponse
import com.example.cuanku.source.TargetsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.http.Part
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

    //    suspend fun addTargets(request: AddTargetRequest): Flow<NetworkResult<AddTargetResponse>> {
//        return flow {
//            emit(NetworkResult.Loading())
//            emit(safeApiCall {
//                dataSource.addTargets(request)
//            })
//        }.flowOn(Dispatchers.IO)
//    }
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

//    suspend fun addTargets(
//        name: MultipartBody.Part,
//        duration: MultipartBody.Part,
//        remaining: MultipartBody.Part,
//        image: MultipartBody.Part,
//        nominal: MultipartBody.Part
//    ): Flow<NetworkResult<AddTargetResponse>> {
//        return flow {
//            emit(NetworkResult.Loading())
//            emit(safeApiCall {
//                dataSource.addTargets(
//                    name, duration, remaining, image, nominal
//                )
//            })
//        }.flowOn(Dispatchers.IO)
//    }

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

}