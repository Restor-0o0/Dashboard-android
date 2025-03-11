package com.example.core.data.repository

import android.util.Log
import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.model.AppError
import retrofit2.HttpException
import java.io.IOException

val TAG = "safeApiCall"

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseWrapper<T> {
    return try{
        val response = apiCall()
        return ResponseWrapper.Success(response)
    }catch(he: HttpException){
        Log.e(TAG,he.message())
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
        Log.e(TAG,ioe.message.toString())
        return ResponseWrapper.Error(AppError.NetworkError)
    }catch (e: Exception){
        Log.e(TAG,e.message.toString())
        return ResponseWrapper.Error(AppError.ValidationError)
    }
}