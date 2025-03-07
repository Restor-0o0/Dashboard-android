package com.example.dashboard.DI

import com.example.dashboard.data.datasource.SettingsService
import com.example.dashboard.data.repository.SettingsRepositoryImpl
import com.example.dashboard.domain.api.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface SettingsRepositoryModule {
    @Binds
    fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
    /*@Provides
    fun provideSettingsRepository(api: SettingsService): SettingsRepositoryImpl{
        return SettingsRepositoryImpl(api)
    }*/


}