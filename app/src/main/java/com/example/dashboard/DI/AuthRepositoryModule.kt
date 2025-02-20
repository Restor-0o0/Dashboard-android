package com.example.dashboard.DI

import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class AuthRepositoryModule {
    @Provides
    fun provideAuthRepository(api: AuthService): AuthRepositoryImpl{
        return AuthRepositoryImpl(api)
    }
}