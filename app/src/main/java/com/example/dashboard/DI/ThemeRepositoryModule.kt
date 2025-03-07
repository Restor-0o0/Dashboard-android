package com.example.dashboard.DI

import com.example.dashboard.data.repository.ThemeRepositoryImpl
import com.example.dashboard.domain.api.ThemeRepository
import dagger.Binds
import dagger.Module

@Module
interface ThemeRepositoryModule {
    @Binds
    fun bindThemeRepository(impl: ThemeRepositoryImpl ): ThemeRepository
}