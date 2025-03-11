package com.example.dashboard.Core

import android.app.Application
import com.example.core.DI.CoreComponent
import com.example.dash.DI.DashboardComponent
import com.example.dashboard.DI.AppComponent
import com.example.dashboard.DI.AppModule
import com.example.dashboard.DI.DaggerAppComponent
import com.example.login.DI.LoginComponent
import com.example.settings.DI.SettingsComponent
import dagger.internal.DaggerCollections


class Application : Application() {

    lateinit var appComponent: AppComponent
    var coreComponent: CoreComponent? = null
    var loginComponent: LoginComponent? = null
    var dashComponent: DashboardComponent? = null
    var settingsComponent: SettingsComponent? = null


    @Override
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(
            appModule = AppModule(this),
        )
    }



    fun initCoreComponent(){
        if(coreComponent == null){
            coreComponent = appComponent.coreComponent().create()
            coreComponent!!.inject(this)
        }
    }
    fun initLoginComponent(){
        if(loginComponent == null){
            loginComponent = appComponent.loginComponent().create()
            loginComponent!!.inject(this)
        }

    }
    fun initDashComponent(){
        if(dashComponent == null){
            dashComponent = appComponent.dashboardComponent().create()
            dashComponent!!.inject(this)
        }
    }
    fun initSettingsComponent(){
        if(settingsComponent == null){
            settingsComponent = appComponent.settingsComponent().create()
            settingsComponent!!.inject(this)
        }
    }

    fun clearCoreComponent(){
        coreComponent = null
    }
    fun clearDashComponent(){
        dashComponent = null
    }
    fun clearLoginComponent(){
        loginComponent = null
    }
    fun clearSettingsComponent(){
        settingsComponent = null
    }
}