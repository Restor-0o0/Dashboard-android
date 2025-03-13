package com.example.settings.domain.usecase

import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.domain.model.AppError
import com.example.core.domain.model.DataWrapper
import com.example.settings.domain.api.SettingsRepository
import com.example.settings.domain.model.SettingsData
import com.example.settings.domain.model.SettingsItem
import javax.inject.Inject

private val TAG = "GetSettingsUseCase"

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
    private val secureTokenRepository: SecureTokenRepository
) {



    suspend fun loadSettings(): DataWrapper<SettingsData> {
        val token = secureTokenRepository.getToken()
        token?.let{
            val settings: ResponseWrapper<SettingsData> = repository.getSettingsData(token)
            when(settings) {
                is ResponseWrapper.Success -> {
                    return DataWrapper.Success(settings.data)
                }
                is ResponseWrapper.Error -> {
                    return DataWrapper.Error(settings.error)
                }
            }
        } ?: return DataWrapper.Error(AppError.TokenNotFound)
    }
}