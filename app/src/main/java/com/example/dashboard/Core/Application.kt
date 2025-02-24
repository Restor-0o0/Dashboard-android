package com.example.dashboard.Core

import android.app.Application
import com.example.dashboard.DI.AppComponent
import com.example.dashboard.DI.DaggerAppComponent
import dagger.hilt.android.AndroidEntryPoint
import dagger.internal.DaggerCollections
import dagger.internal.DaggerGenerated

@AndroidEntryPoint
class Application : Application() {

    lateinit var appComponent: AppComponent

    @Override
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

    }
}