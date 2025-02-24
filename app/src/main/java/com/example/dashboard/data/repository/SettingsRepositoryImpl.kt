package com.example.dashboard.data.repository

import com.example.dashboard.data.datasource.SettingsService
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.api.SettingsRepository
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.TypesCount
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val api: SettingsService
): SettingsRepository{
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
        data: List<SettingsData>
    ): ResponseWrapper<Unit> {
        return safeApiCall {api.setSettingsData(
                token,
                data
            )
        }
    }

}