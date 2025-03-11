package com.example.settings.DI

import androidx.lifecycle.ViewModel
import com.example.core.common.ViewModelKey
import com.example.settings.presenter.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindsSettingsViewModel(viewModel: SettingsViewModel):ViewModel
}