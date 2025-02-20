package com.example.dashboard.presenter.vm

import androidx.lifecycle.ViewModel
import com.example.dashboard.domain.UseCase.GetSettingsUseCase
import com.example.dashboard.domain.UseCase.SetSettingsUseCase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase
): ViewModel() {

}