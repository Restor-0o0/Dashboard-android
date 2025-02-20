package com.example.dashboard.DI

import com.example.dashboard.Core.Application
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)
}