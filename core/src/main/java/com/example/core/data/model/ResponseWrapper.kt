package com.example.core.data.model

import com.example.core.domain.model.AppError


sealed class ResponseWrapper<out T> {
    data class Success<T>(val data: T) : ResponseWrapper<T>()
    data class Error(val error: AppError) : ResponseWrapper<Nothing>()
}