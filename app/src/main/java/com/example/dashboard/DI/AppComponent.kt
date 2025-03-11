package com.example.dashboard.DI


import androidx.compose.ui.text.font.FontVariation
import com.example.core.DI.CoreComponent
import com.example.core.DI.CoreModule
import com.example.core.presenter.Screen
import com.example.dash.DI.DashModule
import com.example.dash.DI.DashboardComponent
import com.example.dashboard.Core.Application
import com.example.dashboard.presenter.MainActivity
import com.example.login.DI.LoginComponent
import com.example.login.DI.LoginModule
import com.example.settings.DI.SettingsComponent
import com.example.settings.DI.SettingsModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CoreModule::class,
        LoginModule::class,
        DashModule::class,
        SettingsModule::class
    ]
)
interface AppComponent {
    fun inject(application: Application)
    fun inject(activity: MainActivity)

    fun coreComponent(): CoreComponent.Factory
    fun dashboardComponent() : DashboardComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
    @Component.Factory
    interface Factory {
        fun create(
            appModule: AppModule
        ): AppComponent
    }
}