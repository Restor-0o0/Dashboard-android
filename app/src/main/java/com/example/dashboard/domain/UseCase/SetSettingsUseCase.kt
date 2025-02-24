package com.example.dashboard.domain.UseCase

import android.util.Log
import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.common.exception.TokenNotFoundException
import com.example.dashboard.data.NetworkConnectionInterceptor
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.SecureTokenRepository
import com.example.dashboard.domain.api.SettingsRepository
import com.example.dashboard.domain.model.AppError
import com.example.dashboard.domain.model.DataWrapper
import com.example.dashboard.domain.model.SettingsData
import jakarta.inject.Inject


private val TAG = "SetSettingsUseCase"

class SetSettingsUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val settingsRepository: SettingsRepository
) {

    suspend fun setSettings(settingsData: List<SettingsData>): DataWrapper<Unit>{
        val token = secureTokenRepository.getToken()
        token?.let{
            val response = settingsRepository.setSettingsData(
                token,
                settingsData
            )
            when (response){
                is ResponseWrapper.Success -> {
                    return DataWrapper.Success(Unit)
                }
                is ResponseWrapper.Error -> {
                    return DataWrapper.Error(response.error)
                }
            }

        } ?: return DataWrapper.Error(AppError.TokenNotFound)
    }
}