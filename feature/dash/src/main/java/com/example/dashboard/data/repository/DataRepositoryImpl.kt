package com.example.dashboard.data.repository

import com.example.core.data.model.ResponseWrapper
import com.example.core.data.repository.safeApiCall
import com.example.dashboard.domain.model.ResponseDashData
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val api: com.example.dashboard.data.datasource.DashDataService
) : com.example.dashboard.domain.api.DataRepository {
    override suspend fun getData(token: String): ResponseWrapper<ResponseDashData> {
        return safeApiCall {api.getData(
                token
            )
        }
    }

}