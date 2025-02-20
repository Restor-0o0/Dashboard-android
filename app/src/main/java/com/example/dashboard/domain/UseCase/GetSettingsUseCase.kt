package com.example.dashboard.domain.UseCase

import android.util.Log
import com.example.dashboard.common.SaveManager
import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.common.exception.TokenNotFoundException
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.SettingsResponse
import com.example.dashboard.domain.api.SettingsRepository
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount
import javax.inject.Inject
import kotlin.jvm.Throws


private val TAG = "GetSettingsUseCase"

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository,
    private val saveManager: SaveManager
) {

    private lateinit var settings_data: Array<SettingsData>
    private lateinit var drawing_types: Array<DrawingTypes>
    private lateinit var types_count: Array<TypesCount>


    fun getSettingsData(): Array<SettingsData> = settings_data
    fun getDrawingTypes(): Array<DrawingTypes> = drawing_types
    fun getTypesCount(): Array<TypesCount> = types_count

    @Throws(
        NoInternetException::class,
        Exception::class,
        TokenNotFoundException::class,

    )
    suspend fun loadSettings()  {
        val token = saveManager.get(TOKEN_SAVE_NAME)
        token?.let{
            val settings: ResponseWrapper<SettingsResponse> = repository.getSettingsData(token)
            when(settings){
                is ResponseWrapper.Success -> {
                    settings_data = settings.data.settings_data
                    drawing_types = settings.data.drawing_types
                    types_count = settings.data.types_count
                }
                is ResponseWrapper.Error -> {
                    Log.e(TAG,settings.message)
                    throw Exception(settings.message)
                }
                ResponseWrapper.Loading -> {

                }
            }
        } ?: {
            throw TokenNotFoundException()
        }
    }
}