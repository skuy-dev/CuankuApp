package com.example.cuanku.repository

import com.example.cuanku.base.BaseApiResponse
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.request.AddCatatanRequest
import com.example.cuanku.request.AddKategoriBookRequest
import com.example.cuanku.response.AddCatatanResponse
import com.example.cuanku.response.DailyCatatanResponse
import com.example.cuanku.response.KategoriBookResponse
import com.example.cuanku.response.ListKategoriBookResponse
import com.example.cuanku.source.BookDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(private val dataSource: BookDataSource) :
    BaseApiResponse() {

    suspend fun addCatatan(request: AddCatatanRequest): Flow<NetworkResult<AddCatatanResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.addCatatan(request)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDailyCatatan(): Flow<NetworkResult<DailyCatatanResponse>> {
        return flow {
            emit(NetworkResult.Loading())
                emit(safeApiCall {
                    dataSource.getDailyCatatan()
                })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addKategori(
        request: AddKategoriBookRequest
    ): Flow<NetworkResult<KategoriBookResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.addKategori(request)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListKategori(): Flow<NetworkResult<ListKategoriBookResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getListKategori()
            })
        }.flowOn(Dispatchers.IO)
    }

}