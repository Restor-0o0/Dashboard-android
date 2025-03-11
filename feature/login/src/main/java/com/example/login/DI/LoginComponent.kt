package com.example.login.DI

import android.app.Application
import dagger.Subcomponent


@Subcomponent(
    modules = [

    ]
)
interface LoginComponent {
    fun inject(application: Application)

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginComponent
    }
}