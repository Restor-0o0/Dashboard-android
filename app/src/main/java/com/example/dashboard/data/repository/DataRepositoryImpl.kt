package com.example.dashboard.data.repository

import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.DataRepository
import com.example.dashboard.domain.model.Dashdata
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val api: DashDataService
) : DataRepository {
    override suspend fun getData(token: String): ResponseWrapper<Dashdata> {
        return safeApiCall {api.getData(
                token
            )
        }
    }

}