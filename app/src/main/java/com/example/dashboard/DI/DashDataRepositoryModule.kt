package com.example.dashboard.DI

import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DashDataRepositoryModule {
    @Provides
    fun provideDashDataRepository(api: DashDataService): DataRepositoryImpl {
        return DataRepositoryImpl(api)
    }
}