package com.example.dashboard.DI

import androidx.lifecycle.ViewModel
import com.example.dashboard.presenter.vm.AuthViewModel
import com.example.dashboard.presenter.vm.DashViewModel
import com.example.dashboard.presenter.vm.SettingsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class) // Связываем ViewModel с ключом
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashViewModel::class) // Связываем ViewModel с ключом
    fun bindDashViewModel(viewModel: DashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class) // Связываем ViewModel с ключом
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)