package com.example.dashboard.DI

import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.datasource.SettingsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideDashDataApiService(retrofit: Retrofit): DashDataService {
        return retrofit.create(DashDataService::class.java)
    }
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
    @Provides
    @Singleton
    fun provideSettingsApiService(retrofit: Retrofit): SettingsService {
        return retrofit.create(SettingsService::class.java)
    }
}