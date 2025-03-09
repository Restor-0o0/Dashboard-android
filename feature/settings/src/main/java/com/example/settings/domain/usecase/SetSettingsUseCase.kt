package com.example.settings.domain.usecase

import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.domain.model.AppError
import com.example.core.domain.model.DataWrapper
import com.example.settings.domain.api.SettingsRepository
import javax.inject.Inject

private val TAG = "SetSettingsUseCase"

class SetSettingsUseCase @Inject constructor(
    private val secureTokenRepository: com.example.core.domain.api.SecureTokenRepository,
    private val settingsRepository: SettingsRepository
) {

    suspend fun setSettings(settingsData: List<com.example.settings.domain.model.SettingsData>): DataWrapper<Unit> {
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