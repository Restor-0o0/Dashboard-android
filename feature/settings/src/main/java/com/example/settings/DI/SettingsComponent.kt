package com.example.settings.DI

import dagger.Subcomponent


@Subcomponent(
    modules = [
        SettingsApiModule::class,
        SettingsRepositoryModule::class
    ]
)
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): SettingsComponent
    }
}