package com.example.dashboard.domain.UseCase

import android.util.Log
import com.example.dashboard.common.SaveManager
import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.common.exception.TokenNotFoundException
import com.example.dashboard.data.NetworkConnectionInterceptor
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.SettingsRepository
import com.example.dashboard.domain.model.SettingsData
import jakarta.inject.Inject


private val TAG = "SetSettingsUseCase"

class SetSettingsUseCase @Inject constructor(
    private val saveManager: SaveManager,
    private val settingsRepository: SettingsRepository
) {

    @Throws(
        Exception::class,
        NoInternetException::class,
        TokenNotFoundException::class
    )
    suspend fun setSettings(settingsData: List<SettingsData>){
        val token = saveManager.get(TOKEN_SAVE_NAME)
        token?.let{
            val response = settingsRepository.setSettingsData(
                token,
                settingsData
            )
            when (response){
                is ResponseWrapper.Success -> {

                }
                is ResponseWrapper.Error -> {
                    Log.e(TAG,response.message)
                    throw Exception(response.message)
                }
                ResponseWrapper.Loading -> {

                }
            }

        } ?: {
            throw TokenNotFoundException()
        }
    }
}