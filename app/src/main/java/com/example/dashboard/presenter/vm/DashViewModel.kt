package com.example.dashboard.presenter.vm

import androidx.lifecycle.ViewModel
import com.example.dashboard.domain.UseCase.GetDashDataUseCase
import com.example.dashboard.domain.UseCase.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashViewModel @Inject constructor(
    private val getDashDataUseCase: GetDashDataUseCase
) : ViewModel(

) {
}