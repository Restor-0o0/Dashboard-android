package com.example.dash.DI

import com.example.dash.data.datasource.DashDataService
import com.example.dash.data.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class DashDataRepositoryModule {
    @Provides
    fun provideDashDataRepository(api: DashDataService): DataRepositoryImpl {
        return DataRepositoryImpl(api)
    }
}