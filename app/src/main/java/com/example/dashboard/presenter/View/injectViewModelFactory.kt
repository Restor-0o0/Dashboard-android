package com.example.dashboard.presenter.View

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.dashboard.Core.Application

@Composable
fun injectViewModelFactory(): ViewModelProvider.Factory {
    val app = LocalContext.current.applicationContext as Application
    return remember { app.appComponent.viewModelFactory() }
}