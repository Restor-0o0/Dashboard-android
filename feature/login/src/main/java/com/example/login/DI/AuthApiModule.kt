package com.example.login.DI


import com.example.login.data.datasource.AuthService

import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class AuthApiModule {

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

}