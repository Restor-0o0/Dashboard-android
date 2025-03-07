package com.example.dashboard.DI

import com.example.dashboard.Core.Application
import com.example.dashboard.presenter.View.MainActivity
import dagger.BindsInstance
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
    fun viewModelFactory(): ViewModelFactory
}