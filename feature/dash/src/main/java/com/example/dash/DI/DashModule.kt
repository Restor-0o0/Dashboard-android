package com.example.dash.DI

import dagger.Module

@Module(
    includes = [
        DashApiModule::class,
        DashDataRepositoryModule::class,
        DataRepositoryModule::class,
        DashViewModelModule::class
    ]
)
class DashModule {
}