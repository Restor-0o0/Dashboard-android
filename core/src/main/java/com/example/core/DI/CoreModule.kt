package com.example.core.DI

import dagger.Module

@Module(
    includes =[
        NetworkModule::class,
        SecureTokenRepositoryModule::class,
        ThemeRepositoryModule::class,
    ]
)
class CoreModule {
}