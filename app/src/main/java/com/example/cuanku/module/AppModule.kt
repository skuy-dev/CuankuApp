package com.example.cuanku.module

import android.content.Context
import com.chad.library.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.cuanku.helper.AppManager
import com.example.cuanku.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://cuanku.skuy.dev/api/v1/")
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        interceptor: NetworkInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(if (BuildConfig.DEBUG) chuckerInterceptor else chuckerInterceptor)
            .connectTimeout(240, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .readTimeout(240, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideChucker(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context)
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Provides
    @Singleton
    fun providePrefManager(@ApplicationContext context: Context) = AppManager(context)

}