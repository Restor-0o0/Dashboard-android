package com.example.dashboard.presenter.vm

import androidx.lifecycle.ViewModel
import com.example.dashboard.domain.UseCase.GetDashDataUseCase
import com.example.dashboard.domain.UseCase.GetSettingsUseCase
import javax.inject.Inject

class DashViewModel @Inject constructor(
    private val getDashDataUseCase: GetDashDataUseCase
) : ViewModel(

) {
}