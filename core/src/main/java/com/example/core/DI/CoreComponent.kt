package com.example.core.DI

import dagger.Subcomponent


@Subcomponent(
    modules = [
        LocalDataStoreModule::class,
        NetworkModule::class,
        SecureTokenRepositoryModule::class,
        ThemeRepositoryModule::class,

    ]
)
interface CoreComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): CoreComponent
    }
}