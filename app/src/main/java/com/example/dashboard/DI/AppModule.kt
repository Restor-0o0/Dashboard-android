package com.example.dashboard.DI

import android.app.Application
import android.content.Context
import com.example.core.DI.CoreComponent
import com.example.dash.DI.DashboardComponent
import com.example.login.DI.LoginComponent
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context{
        return application.applicationContext
    }
}