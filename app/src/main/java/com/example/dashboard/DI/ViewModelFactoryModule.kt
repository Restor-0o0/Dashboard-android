package com.example.dashboard.DI

import androidx.lifecycle.ViewModelProvider
import com.example.dashboard.presenter.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}