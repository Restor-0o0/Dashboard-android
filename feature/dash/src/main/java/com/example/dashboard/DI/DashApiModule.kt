package com.example.dashboard.DI


import com.example.dashboard.data.datasource.DashDataService

import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class DashApiModule {
    @Provides
    @Singleton
    fun provideDashDataApiService(retrofit: Retrofit): DashDataService {
        return retrofit.create(DashDataService::class.java)
    }
}