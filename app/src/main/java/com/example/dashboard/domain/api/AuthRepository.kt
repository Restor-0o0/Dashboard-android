package com.example.dashboard.domain.api

import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.User

interface AuthRepository {
    suspend fun login(username: String,password: String) : ResponseWrapper<User>
}