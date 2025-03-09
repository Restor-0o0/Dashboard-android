package com.example.core.DI


import com.example.core.data.repository.ThemeRepositoryImpl
import com.example.core.domain.api.ThemeRepository
import dagger.Binds
import dagger.Module

@Module
interface ThemeRepositoryModule {
    @Binds
    fun bindThemeRepository(impl: ThemeRepositoryImpl): ThemeRepository
}