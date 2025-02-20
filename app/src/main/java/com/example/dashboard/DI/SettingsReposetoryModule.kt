package com.example.dashboard.DI

import com.example.dashboard.data.datasource.SettingsService
import com.example.dashboard.data.reposetory.SettingsReposetoryImpl
import dagger.Module
import dagger.Provides

@Module
class SettingsReposetoryModule {
    @Provides
    fun provideSettingsReposetory(api: SettingsService): SettingsReposetoryImpl{
        return SettingsReposetoryImpl(api)
    }
}