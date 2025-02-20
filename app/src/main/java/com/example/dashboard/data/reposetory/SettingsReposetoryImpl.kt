package com.example.dashboard.data.reposetory

import com.example.dashboard.data.datasource.SettingsService
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.SettingsResponse
import com.example.dashboard.domain.api.SettingsReposetory
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount
import retrofit2.Response
import javax.inject.Inject

class SettingsReposetoryImpl @Inject constructor(
    private val api: SettingsService
): SettingsReposetory{
    override suspend fun getTypesCount(token: String): ResponseWrapper<List<TypesCount>> {
        return try{
            val response = api.getTypesCount(
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

    override suspend fun getDrawingTypes(token: String): ResponseWrapper<List<DrawingTypes>> {
        return try{
            val response = api.getDrawingTypes(
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

    override suspend fun getSettingsData(token: String): ResponseWrapper<SettingsResponse> {
        return try{
            val response = api.getSettingsData(
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

    override suspend fun setSettingsData(
        token: String,
        data: List<SettingsData>
    ): ResponseWrapper<Nothing> {
        return try{
            val response = api.setSettingsData(
                token,
                data
            )
            if(response.isSuccessful){
                return ResponseWrapper.Success(response.body()!!) as Nothing
            }else{
                return ResponseWrapper.Error(response.message())
            }
        }catch (e: Exception){
            return  ResponseWrapper.Error(e.message.toString())
        }
    }

}