package com.example.dashboard.domain.api

import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.TypesCount

interface SettingsRepository {
    suspend fun getTypesCount(token: String): ResponseWrapper<List<TypesCount>>
    suspend fun getDrawingTypes(token: String): ResponseWrapper<List<DrawingTypes>>
    suspend fun getSettingsData(token: String): ResponseWrapper<SettingsData>
    suspend fun setSettingsData(token: String,data: List<SettingsData>): ResponseWrapper<Unit>
}