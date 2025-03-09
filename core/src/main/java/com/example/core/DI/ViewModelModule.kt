package com.example.core.DI

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass
/*
@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(com.example.settings.presenter.AuthViewModel::class) // Связываем ViewModel с ключом
    fun bindAuthViewModel(viewModel: com.example.settings.presenter.AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(com.example.dashboard.presenter.DashViewModel::class) // Связываем ViewModel с ключом
    fun bindDashViewModel(viewModel: com.example.dashboard.presenter.DashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(com.example.login.presenter.SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: com.example.login.presenter.SettingsViewModel): ViewModel
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)*/