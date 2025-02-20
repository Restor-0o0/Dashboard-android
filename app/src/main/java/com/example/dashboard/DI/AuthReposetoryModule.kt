package com.example.dashboard.DI

import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.reposetory.AuthReposetoryImpl
import com.example.dashboard.domain.api.AuthReposetory
import dagger.Module
import dagger.Provides

@Module
class AuthReposetoryModule {
    @Provides
    fun provideAuthReposetory(api: AuthService): AuthReposetoryImpl{
        return AuthReposetoryImpl(api)
    }
}