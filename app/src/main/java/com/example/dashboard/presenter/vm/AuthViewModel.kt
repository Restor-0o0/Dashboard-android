package com.example.dashboard.presenter.vm

import androidx.lifecycle.ViewModel
import com.example.dashboard.domain.UseCase.AuthUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

}