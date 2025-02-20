package com.example.dashboard.DI

import com.example.dashboard.common.IP_HOST
import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.datasource.SettingsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(IP_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}