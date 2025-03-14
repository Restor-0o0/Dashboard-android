package com.example.core.domain.UseCase

import com.example.core.data.repository.SecureTokenRepositoryImpl
import javax.inject.Inject

class TokenUseCase @Inject constructor(
    private val secureTokenRepositoryImpl: SecureTokenRepositoryImpl
) {
    fun tokenExist(): Boolean{
        return secureTokenRepositoryImpl.getToken() != null
    }

    fun clearToken(){
        secureTokenRepositoryImpl.clearToken()
    }
}