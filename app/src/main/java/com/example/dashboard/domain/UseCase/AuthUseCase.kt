package com.example.dashboard.domain.UseCase

import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.User
import com.example.dashboard.domain.api.AuthRepository
import com.example.dashboard.domain.api.SecureTokenRepository
import com.example.dashboard.domain.model.AppError
import com.example.dashboard.domain.model.DataWrapper
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val authRepository: AuthRepository
) {

    suspend fun login(
        username: String,
        password: String
    ): DataWrapper<User>{
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