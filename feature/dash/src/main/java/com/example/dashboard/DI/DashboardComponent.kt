package com.example.dashboard.DI

import dagger.Subcomponent

@Subcomponent(
    modules = [
    DashApiModule::class,
    DashDataRepositoryModule::class,
    DataRepositoryModule::class
    ]
)
interface DashboardComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : DashboardComponent
    }
}