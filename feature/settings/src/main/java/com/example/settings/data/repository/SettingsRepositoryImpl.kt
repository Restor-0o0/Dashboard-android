package com.example.settings.data.repository

import android.util.Log
import com.example.core.data.model.ResponseWrapper
import com.example.core.data.repository.safeApiCall
import com.example.settings.common.MODULE_TAG
import com.example.settings.data.datasource.SettingsService
import com.example.settings.domain.api.SettingsRepository
import com.example.settings.domain.model.DrawingTypes
import com.example.settings.domain.model.SettingsData
import com.example.settings.domain.model.SettingsItem
import com.example.settings.domain.model.TypesCount
import javax.inject.Inject

private val TAG = "SettingsRepositoryImpl"

class SettingsRepositoryImpl @Inject constructor(
    private val api: SettingsService
): SettingsRepository {
    override suspend fun getTypesCount(token: String): ResponseWrapper<List<TypesCount>> {
        Log.d("$MODULE_TAG/$TAG","getTypesCount")
        return safeApiCall {api.getTypesCount(
                token
            )
        }
    }

    override suspend fun getDrawingTypes(token: String): ResponseWrapper<List<DrawingTypes>> {
        Log.d("$MODULE_TAG/$TAG","getDrawingTypes")
        return safeApiCall {api.getDrawingTypes(
                token
            )
        }
    }

    override suspend fun getSettingsData(token: String): ResponseWrapper<SettingsData> {
        Log.d("$MODULE_TAG/$TAG","getSettingsData")
        return safeApiCall {api.getSettingsData(
            "Token $token"
            )
        }
    }

    override suspend fun setSettingsData(
        token: String,
        data: List<SettingsItem>
    ): ResponseWrapper<Unit> {
        Log.d("$MODULE_TAG/$TAG","setSettingsData")
        return safeApiCall {api.setSettingsData(
                token,
                data
            )
        }
    }

}