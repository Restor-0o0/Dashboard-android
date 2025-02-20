package com.example.dashboard.DI

import com.example.dashboard.Core.Application
import dagger.BindsInstance
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): AppComponent
    }
    fun viewModelFactory(): ViewModelFactory
}