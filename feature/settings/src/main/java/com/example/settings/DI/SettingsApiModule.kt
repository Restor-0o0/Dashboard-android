package com.example.settings.DI


import com.example.settings.data.datasource.SettingsService
import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class SettingsApiModule {

    @Provides
    @Singleton
    fun provideSettingsApiService(retrofit: Retrofit): SettingsService {
        return retrofit.create(SettingsService::class.java)
    }
}