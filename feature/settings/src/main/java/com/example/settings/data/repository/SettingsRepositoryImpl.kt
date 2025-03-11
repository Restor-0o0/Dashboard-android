package com.example.settings.data.repository

import com.example.core.data.model.ResponseWrapper
import com.example.core.data.repository.safeApiCall
import com.example.settings.data.datasource.SettingsService
import com.example.settings.domain.api.SettingsRepository
import com.example.settings.domain.model.DrawingTypes
import com.example.settings.domain.model.SettingsData
import com.example.settings.domain.model.SettingsItem
import com.example.settings.domain.model.TypesCount
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val api: SettingsService
): SettingsRepository {
    override suspend fun getTypesCount(token: String): ResponseWrapper<List<TypesCount>> {
        return safeApiCall {api.getTypesCount(
                token
            )
        }
    }

    override suspend fun getDrawingTypes(token: String): ResponseWrapper<List<DrawingTypes>> {
        return safeApiCall {api.getDrawingTypes(
                token
            )
        }
    }

    override suspend fun getSettingsData(token: String): ResponseWrapper<SettingsData> {
        return safeApiCall {api.getSettingsData(
                token
            )
        }
    }

    override suspend fun setSettingsData(
        token: String,
        data: List<SettingsItem>
    ): ResponseWrapper<Unit> {
        return safeApiCall {api.setSettingsData(
                token,
                data
            )
        }
    }

}