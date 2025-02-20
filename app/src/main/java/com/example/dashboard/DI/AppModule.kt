package com.example.dashboard.DI

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    NetworkModule::class,
    ApiModule::class,
    AuthRepositoryModule::class,
    DashDataRepositoryModule::class,
    SettingsRepositoryModule::class,
    SaveManagerModule::class
])
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context{
        return application.applicationContext
    }
}