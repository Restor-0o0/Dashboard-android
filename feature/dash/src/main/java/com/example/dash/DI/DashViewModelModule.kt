package com.example.dash.DI

import androidx.lifecycle.ViewModel
import com.example.core.common.ViewModelKey
import com.example.dash.presenter.DashViewModel
import com.example.dash.presenter.DashViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DashViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashViewModel::class)
    fun bindDashViewModel(viewModel:DashViewModel) : ViewModel
}
