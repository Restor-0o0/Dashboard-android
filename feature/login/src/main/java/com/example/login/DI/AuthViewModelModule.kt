package com.example.login.DI

import androidx.lifecycle.ViewModel
import com.example.core.common.ViewModelKey
import com.example.login.presenter.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindsAuthViewModel(viewModel:AuthViewModel):ViewModel

}