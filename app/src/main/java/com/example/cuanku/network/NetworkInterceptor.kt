package com.example.cuanku.network

import com.example.cuanku.helper.AppManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(val prefs: AppManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val modified = original.newBuilder()

        modified.addHeader("Content-Type", "application/json")
        modified.addHeader("accept", "application/json")
        modified.addHeader("Connection", "close")
        modified.addHeader("Accept-Language", "id")
        modified.addHeader("issuer", "MOBILEAPP")

        prefs.getToken()?.let {
            modified.addHeader("Authorization", "Bearer $it")
        }

        modified.method(original.method, original.body)

        val request: Request = modified.build()
        return chain.proceed(request)

    }
}