package com.example.dashboard.data.reposetory

import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.DataReposetory
import com.example.dashboard.domain.model.Dashdata
import javax.inject.Inject

class DataReposetoryImpl @Inject constructor(
    private val api: DashDataService
) : DataReposetory {
    override suspend fun getData(token: String?): ResponseWrapper<Dashdata> {
        return try{
            val response = api.getData(
                token
            )
            if(response.isSuccessful){
                return ResponseWrapper.Success(response.body()!!)
            }else{
                return ResponseWrapper.Error(response.message())
            }
        }catch (e: Exception){
            return  ResponseWrapper.Error(e.message.toString())
        }
    }

}