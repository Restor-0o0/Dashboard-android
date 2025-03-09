package com.example.core.DI

import com.example.core.data.repository.LocalDataStoreImpl
import com.example.core.domain.api.LocalDataStore

import dagger.Binds
import dagger.Module

@Module
interface LocalDataStoreModule {
    @Binds
    fun bindLocalDataStoreImpl(impl: LocalDataStoreImpl) : LocalDataStore
}