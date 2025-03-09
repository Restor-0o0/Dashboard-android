package com.example.dashboard.domain.api

import com.example.core.data.model.ResponseWrapper
import com.example.dashboard.domain.model.ResponseDashData

interface DataRepository {
    suspend fun getData(token: String): ResponseWrapper<ResponseDashData>
}