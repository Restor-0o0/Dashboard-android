package com.example.dashboard.DI

import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.reposetory.DataReposetoryImpl
import dagger.Module
import dagger.Provides

@Module
class DashDataReposetoryModule {
    @Provides
    fun provideDashDataReposetory(api: DashDataService):DataReposetoryImpl{
        return DataReposetoryImpl(api)
    }
}