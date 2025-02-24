package com.example.dashboard.data.datasource

import com.example.dashboard.common.DATA_LIST
import com.example.dashboard.common.DRAW_LIST
import com.example.dashboard.common.SETT_LIST
import com.example.dashboard.common.TYPES_LIST
import com.example.dashboard.domain.model.Dashdata
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT


interface DashDataService{ //Интерфейс для обмена данными
    @GET(DATA_LIST)
    suspend fun getData( //Получение данных
        @Header("Authorization") token: String?
    ): Dashdata

}