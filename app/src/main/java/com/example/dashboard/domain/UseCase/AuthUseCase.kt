package com.example.dashboard.domain.UseCase

import com.example.dashboard.common.SaveManager
import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.AuthRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class AuthUseCase @Inject constructor(
    private val saveManager: SaveManager,
    private val authRepository: AuthRepository
) {

    @Throws(
        NoInternetException::class,
        Exception::class
    )
    suspend fun login(
        username: String,
        password: String
    ): Boolean{
        val response = authRepository.login(
            username,
            password
        )
        when (response){
            is ResponseWrapper.Success -> {
                saveManager.save(
                    TOKEN_SAVE_NAME,
                    response.data.auth_token
                    )
                return true
            }
            is ResponseWrapper.Error -> {
                throw Exception(response.message)
            }
            ResponseWrapper.Loading -> {
                return false
            }
        }
        return false
    }
}