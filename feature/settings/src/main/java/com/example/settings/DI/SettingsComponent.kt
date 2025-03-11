package com.example.settings.DI

import android.app.Application
import dagger.Subcomponent


@Subcomponent(
    modules = [

    ]
)
interface SettingsComponent {
    fun inject(application: Application)

    @Subcomponent.Factory
    interface Factory{
        fun create(): SettingsComponent
    }
}