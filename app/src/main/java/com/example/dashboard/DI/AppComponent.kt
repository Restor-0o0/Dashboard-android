package com.example.dashboard.DI


import com.example.core.DI.CoreComponent
import com.example.dashboard.presenter.MainActivity
import com.example.login.DI.LoginComponent
import com.example.settings.DI.SettingsComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    @Component.Factory
    interface Factory{
        fun create(appModule: AppModule): AppComponent
    }
    fun coreComponent(): CoreComponent.Factory
    fun dashboardComponent() : DashboardComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
}