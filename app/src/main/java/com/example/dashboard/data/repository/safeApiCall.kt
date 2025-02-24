package com.example.dashboard.data.repository

import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.model.AppError
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseWrapper<T>{
    return try{
        val response = apiCall()
        return ResponseWrapper.Success(response)
    }catch(he: HttpException){
        return when (he.code()){
            400 -> ResponseWrapper.Error(AppError.BadRequest)
            401 -> ResponseWrapper.Error(AppError.Unauthorized)
            403 -> ResponseWrapper.Error(AppError.Forbidden)
            404 -> ResponseWrapper.Error(AppError.NotFound)
            500 -> ResponseWrapper.Error(AppError.ServerError)
            else -> {
                ResponseWrapper.Error(AppError.OtherApiError(
                    he.code(),
                    he.message!!
                ))
            }
        }
    }catch (ioe: IOException) {
        return ResponseWrapper.Error(AppError.NetworkError)
    }catch (e: Exception){
        return ResponseWrapper.Error(AppError.ValidationError)
    }

}