package com.example.dashboard.DI

import com.example.dashboard.data.repository.LocalDataStoreImpl
import com.example.dashboard.domain.api.LocalDataStore
import dagger.Binds
import dagger.Module

@Module
interface LocalDataStoreModule {
    @Binds
    fun bindLocalDataStoreImpl(impl: LocalDataStoreImpl) : LocalDataStore
}