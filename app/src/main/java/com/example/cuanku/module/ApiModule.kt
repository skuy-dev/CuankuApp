package com.example.cuanku.module

import com.example.cuanku.service.AuthServiceInstance
import com.example.cuanku.service.TargetsServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideAuth(
        retrofit: Retrofit
    ) = retrofit.create(AuthServiceInstance::class.java)

    @Provides
    @Singleton
    fun provideTargets(
        retrofit: Retrofit
    ) = retrofit.create(TargetsServiceInstance::class.java)

}