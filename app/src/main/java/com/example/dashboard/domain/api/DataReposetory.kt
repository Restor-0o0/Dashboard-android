package com.example.dashboard.domain.api

import com.example.dashboard.common.DRAW_LIST
import com.example.dashboard.common.SETT_LIST
import com.example.dashboard.common.TYPES_LIST
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.model.Dashdata
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface DataReposetory {
    suspend fun getData(token: String?): ResponseWrapper<Dashdata>
}