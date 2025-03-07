package com.example.dashboard.DI

import com.example.dashboard.data.repository.SecureTokenRepositoryImpl
import com.example.dashboard.domain.api.SecureTokenRepository
import dagger.Binds
import dagger.Module

@Module
interface SecureTokenRepositoryModule {
    @Binds
    fun bindSecureTokenRepositoryModule(impl: SecureTokenRepositoryImpl): SecureTokenRepository
}