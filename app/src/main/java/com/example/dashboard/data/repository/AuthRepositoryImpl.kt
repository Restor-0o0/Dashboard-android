package com.example.dashboard.data.repository

import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.User
import com.example.dashboard.domain.api.AuthRepository
import com.example.dashboard.domain.model.AppError
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService
): AuthRepository {
    override suspend fun login(username: String, password: String): ResponseWrapper<User> {
        return safeApiCall {
            api.login(
                username,
                password
            )
        }
    }
}