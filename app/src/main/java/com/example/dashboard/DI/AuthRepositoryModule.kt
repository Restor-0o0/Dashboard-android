package com.example.dashboard.DI

import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.repository.AuthRepositoryImpl
import com.example.dashboard.domain.api.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface AuthRepositoryModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    /*@Provides
    fun provideAuthRepository(api: AuthService): AuthRepositoryImpl{
        return AuthRepositoryImpl(api)
    }*/
}