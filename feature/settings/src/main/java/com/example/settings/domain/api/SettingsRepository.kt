package com.example.settings.domain.api


import com.example.settings.domain.model.DrawingTypes
import com.example.settings.domain.model.SettingsData
import com.example.settings.domain.model.TypesCount

interface SettingsRepository {
    suspend fun getTypesCount(token: String): com.example.core.data.model.ResponseWrapper<List<TypesCount>>
    suspend fun getDrawingTypes(token: String): com.example.core.data.model.ResponseWrapper<List<DrawingTypes>>
    suspend fun getSettingsData(token: String): com.example.core.data.model.ResponseWrapper<SettingsData>
    suspend fun setSettingsData(token: String,data: List<SettingsData>): com.example.core.data.model.ResponseWrapper<Unit>
}