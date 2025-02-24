package com.example.dashboard.domain.UseCase

import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.common.exception.TokenNotFoundException
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.SecureTokenRepository
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.api.SettingsRepository
import com.example.dashboard.domain.model.AppError
import com.example.dashboard.domain.model.DataWrapper
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.TypesCount
import javax.inject.Inject
import kotlin.jvm.Throws


private val TAG = "GetSettingsUseCase"

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
    private val secureTokenRepository: SecureTokenRepository
) {



    suspend fun loadSettings(): DataWrapper<SettingsData>  {
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