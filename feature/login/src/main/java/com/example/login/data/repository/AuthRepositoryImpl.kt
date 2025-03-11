package com.example.login.data.repository

import android.util.Log
import com.example.core.data.model.ResponseWrapper
import com.example.core.data.repository.safeApiCall
import com.example.login.common.MODULE_TAG
import com.example.login.data.datasource.AuthService
import com.example.login.data.model.User
import com.example.login.domain.api.AuthRepository
import javax.inject.Inject


private val TAG = "AuthRepositoryImpl"

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService
): AuthRepository {
    override suspend fun login(username: String, password: String): ResponseWrapper<User> {
        Log.d("$MODULE_TAG/$TAG","login")
        return safeApiCall {
            api.login(
                username,
                password
            )
        }
    }
}