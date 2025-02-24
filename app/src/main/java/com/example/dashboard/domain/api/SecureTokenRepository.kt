package com.example.dashboard.domain.api

interface SecureTokenRepository {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}