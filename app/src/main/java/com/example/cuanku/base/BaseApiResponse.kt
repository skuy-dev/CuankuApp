package com.example.cuanku.base

import com.example.cuanku.helper.SingleEvent
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            } else {
                val error: BaseResponse<Any> = Gson().fromJson(
                    response.errorBody()?.charStream(),
                    BaseResponse<Any>()::class.java
                )
                error.message?.let {
                    return error(it)
                }
            }
            return error("${response.code()}: ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }

    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(SingleEvent(errorMessage))

}