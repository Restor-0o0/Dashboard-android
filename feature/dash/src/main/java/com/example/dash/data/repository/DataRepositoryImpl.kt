package com.example.dash.data.repository

import com.example.core.data.model.ResponseWrapper
import com.example.core.data.repository.safeApiCall
import com.example.dash.data.datasource.DashDataService
import com.example.dash.domain.api.DataRepository
import com.example.dash.domain.model.ResponseDashData
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val api: DashDataService
) : DataRepository {
    override suspend fun getData(token: String): ResponseWrapper<ResponseDashData> {
        return safeApiCall {api.getData(
            "Token $token"
            )
        }
    }

}