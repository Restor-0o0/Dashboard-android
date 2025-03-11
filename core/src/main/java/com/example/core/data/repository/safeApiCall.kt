package com.example.core.data.repository

import android.util.Log
import com.example.core.common.MODULE_TAG
import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.model.AppError
import retrofit2.HttpException
import java.io.IOException

private val TAG = "safeApiCall"

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseWrapper<T> {
    Log.d("$MODULE_TAG/$TAG","safeApiCall " + apiCall().hashCode().toString())
    return try{
        val response = apiCall()
        return ResponseWrapper.Success(response)
    }catch(he: HttpException){
        Log.e("$MODULE_TAG/$TAG","safeApiCall " + he.message().toString())
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
        Log.e("$MODULE_TAG/$TAG","safeApiCall " + ioe.message.toString())
        return ResponseWrapper.Error(AppError.NetworkError)
    }catch (e: Exception){
        Log.e("$MODULE_TAG/$TAG","safeApiCall " + e.message.toString())
        return ResponseWrapper.Error(AppError.ValidationError)
    }
}