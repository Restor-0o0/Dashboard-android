package com.example.settings.domain.api


import com.example.core.data.model.ResponseWrapper
import com.example.settings.domain.model.DrawingTypes
import com.example.settings.domain.model.SettingsData
import com.example.settings.domain.model.SettingsItem
import com.example.settings.domain.model.TypesCount

interface SettingsRepository {
    suspend fun getTypesCount(token: String): ResponseWrapper<List<TypesCount>>
    suspend fun getDrawingTypes(token: String): ResponseWrapper<List<DrawingTypes>>
    suspend fun getSettingsData(token: String): ResponseWrapper<SettingsData>
    suspend fun setSettingsData(token: String,data: List<SettingsItem>): ResponseWrapper<Unit>
}