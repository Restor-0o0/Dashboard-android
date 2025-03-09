package com.example.dashboard.DI

import android.app.Application
import android.content.Context
import com.example.core.DI.ApiModule
import com.example.core.DI.LocalDataStoreModule
import com.example.core.DI.NetworkModule
import com.example.core.DI.SecureTokenRepositoryModule
import com.example.core.DI.ThemeRepositoryModule
import com.example.core.DI.ViewModelModule
import com.example.login.DI.AuthRepositoryModule
import com.example.settings.DI.SettingsRepositoryModule
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