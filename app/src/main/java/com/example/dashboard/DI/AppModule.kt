package com.example.dashboard.DI

import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [
    NetworkModule::class,
    ApiModule::class,
    AuthReposetoryModule::class,
    DashDataReposetoryModule::class,
    SettingsReposetoryModule::class
])
class AppModule (private val context: Context) {
    @Provides
    fun provideContext(): Context{
        return context
    }
}