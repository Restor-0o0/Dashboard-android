package com.example.dashboard.data.datasource

import com.example.dashboard.common.DRAW_LIST
import com.example.dashboard.common.SETT_LIST
import com.example.dashboard.common.TYPES_LIST
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.TypesCount
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface SettingsService {
    @GET(TYPES_LIST)
    suspend fun getTypesCount( //Получение типов группировки( есть в getSettingsData, можно не кидать)
        @Header("Authorization") token: String?
    ): List<TypesCount>
    @GET(DRAW_LIST)
    suspend fun getDrawingTypes( // Получение типов отрисовки( есть в getSettingsData, можно не кидать)
        @Header("Authorization") token: String?
    ): List<DrawingTypes>
    @GET(SETT_LIST)
    suspend fun getSettingsData( // Получение данных для настройки
        @Header("Authorization") token: String?
    ): SettingsData
    @PUT(SETT_LIST)
    suspend fun setSettingsData( // Отправка данных для отрисовки
        @Header("Authorization") token: String?,
        @Body data: List<SettingsData>
    ): Unit
}