package com.example.core.DI

import dagger.Module
import javax.inject.Singleton

@Module(
    includes =[
        NetworkModule::class,
        SecureTokenRepositoryModule::class,
        ThemeRepositoryModule::class,
    ]
)
class CoreModule {
}