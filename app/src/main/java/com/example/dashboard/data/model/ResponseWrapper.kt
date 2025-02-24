package com.example.dashboard.data.model

import com.example.dashboard.domain.model.AppError

sealed class ResponseWrapper<out T> {
    data class Success<T>(val data: T) : ResponseWrapper<T>()
    data class Error(val error: AppError) : ResponseWrapper<Nothing>()
}