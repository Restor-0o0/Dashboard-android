package com.example.login.domain.usecase

import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.domain.model.AppError
import com.example.core.domain.model.DataWrapper

import com.example.login.data.model.User
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val authRepository: com.example.login.domain.api.AuthRepository


) {

    suspend fun login(
        username: String,
        password: String
    ): DataWrapper<User> {
        val response = authRepository.login(
            username,
            password
        )
        when (response){
            is ResponseWrapper.Success -> {
                secureTokenRepository.saveToken(
                    response.data.auth_token
                    )
                return DataWrapper.Success(response.data)
            }
            is ResponseWrapper.Error -> {
                DataWrapper.Error(response.error)
            }

        }
        return DataWrapper.Error(AppError.ValidationError)
    }
}