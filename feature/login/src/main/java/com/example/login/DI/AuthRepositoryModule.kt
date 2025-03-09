package com.example.login.DI


import com.example.login.domain.api.AuthRepository
import dagger.Binds
import dagger.Module


@Module
interface AuthRepositoryModule {

    @Binds
    fun bindAuthRepository(impl: com.example.login.data.repository.AuthRepositoryImpl): AuthRepository

    /*@Provides
    fun provideAuthRepository(api: AuthService): AuthRepositoryImpl{
        return AuthRepositoryImpl(api)
    }*/
}