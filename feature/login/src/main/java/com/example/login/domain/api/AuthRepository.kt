package com.example.login.domain.api

import com.example.core.data.model.ResponseWrapper

import com.example.login.data.model.User

interface AuthRepository {
    suspend fun login(username: String,password: String) : ResponseWrapper<User>
}