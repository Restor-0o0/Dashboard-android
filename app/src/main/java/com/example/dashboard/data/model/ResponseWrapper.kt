package com.example.dashboard.data.model

sealed class ResponseWrapper<out T> {
    data class Success<T>(val data: T) : ResponseWrapper<T>()
    data class Error(val message: String) : ResponseWrapper<Nothing>()
    object Loading: ResponseWrapper<Nothing>()
}