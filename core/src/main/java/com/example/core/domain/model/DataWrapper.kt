package com.example.core.domain.model

sealed class DataWrapper<out T> {
    data class Success<T>(val data: T): DataWrapper<T>()
    data class Error(val error: AppError): DataWrapper<Nothing>()

}