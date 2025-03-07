package com.example.dashboard.data.datasource

import com.example.dashboard.common.DATA_LIST
import com.example.dashboard.domain.model.ResponseDashData
import retrofit2.http.GET
import retrofit2.http.Header


interface DashDataService{ //Интерфейс для обмена данными
    @GET(DATA_LIST)
    suspend fun getData( //Получение данных
        @Header("Authorization") token: String?
    ): ResponseDashData

}