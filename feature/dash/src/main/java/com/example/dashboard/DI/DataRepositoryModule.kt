package com.example.dashboard.DI

import com.example.dashboard.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataRepositoryModule {
    @Binds
    fun bindDataRepository(impl: DataRepositoryImpl): com.example.dashboard.domain.api.DataRepository
}