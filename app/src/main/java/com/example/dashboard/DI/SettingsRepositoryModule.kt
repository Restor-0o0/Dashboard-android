package com.example.dashboard.DI

import com.example.dashboard.data.datasource.SettingsService
import com.example.dashboard.data.repository.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class SettingsRepositoryModule {
    @Provides
    fun provideSettingsRepository(api: SettingsService): SettingsRepositoryImpl{
        return SettingsRepositoryImpl(api)
    }
}