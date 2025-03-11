package com.example.core.DI


import com.example.core.data.repository.SecureTokenRepositoryImpl
import com.example.core.domain.api.SecureTokenRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface SecureTokenRepositoryModule {
    @Binds
    fun bindSecureTokenRepositoryModule(impl: SecureTokenRepositoryImpl): SecureTokenRepository
}