package com.example.settings.DI

import com.example.settings.presenter.SettingsViewModel
import dagger.Module

@Module(
    includes = [
        SettingsApiModule::class,
        SettingsRepositoryModule::class,
        SettingsViewModelModule::class
    ]
)
class SettingsModule {
}