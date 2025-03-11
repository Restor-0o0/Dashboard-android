package com.example.dash.DI

import com.example.dash.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataRepositoryModule {
    @Binds
    fun bindDataRepository(impl: DataRepositoryImpl): com.example.dash.domain.api.DataRepository
}