package com.example.dash.domain.api

import com.example.core.data.model.ResponseWrapper
import com.example.dash.domain.model.ResponseDashData

interface DataRepository {
    suspend fun getData(token: String): ResponseWrapper<ResponseDashData>
}