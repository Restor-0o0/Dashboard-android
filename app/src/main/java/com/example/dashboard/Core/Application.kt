package com.example.dashboard.Core

import android.app.Application
import com.example.dashboard.DI.AppComponent
import com.example.dashboard.DI.AppModule
import dagger.internal.DaggerCollections
import dagger.internal.DaggerGenerated


class Application : Application() {

    lateinit var appComponent: AppComponent

    @Override
    override fun onCreate() {
        super.onCreate()
        //appComponent = DaggerAppComponent.factory().create(AppModule(this))
    }
}