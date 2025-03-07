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
    ViewModelModule::class,
    LocalDataStoreModule::class,
    SecureTokenRepositoryModule::class,
    ThemeRepositoryModule::class,
    DataRepositoryModule::class
])
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context{
        return application.applicationContext
    }
}