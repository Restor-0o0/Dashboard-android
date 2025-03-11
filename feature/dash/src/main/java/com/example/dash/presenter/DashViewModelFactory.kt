package com.example.dash.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.UseCase.TokenUseCase
import com.example.dash.domain.usecase.GetDashDataUseCase
import javax.inject.Inject

class DashViewModelFactory@Inject constructor(
    private val getDashDataUseCase: GetDashDataUseCase,
    private val tokenExistUseCase: TokenUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashViewModel::class.java)) {
            return DashViewModel(getDashDataUseCase, tokenExistUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}