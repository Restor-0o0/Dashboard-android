package com.example.settings.DI


import com.example.settings.domain.api.SettingsRepository
import dagger.Binds
import dagger.Module


@Module
interface SettingsRepositoryModule {
    @Binds
    fun bindSettingsRepository(impl: com.example.settings.data.repository.SettingsRepositoryImpl): SettingsRepository
    /*@Provides
    fun provideSettingsRepository(api: SettingsService): SettingsRepositoryImpl{
        return SettingsRepositoryImpl(api)
    }*/


}