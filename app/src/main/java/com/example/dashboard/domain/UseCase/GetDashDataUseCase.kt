package com.example.dashboard.domain.UseCase

import android.media.session.MediaSession.Token
import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.common.exception.TokenNotFoundException
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.DataRepository
import com.example.dashboard.domain.api.SecureTokenRepository
import com.example.dashboard.domain.model.AppError
import com.example.dashboard.domain.model.Dashdata
import com.example.dashboard.domain.model.DataWrapper
import javax.inject.Inject
import kotlin.jvm.Throws

class GetDashDataUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val dataRepository: DataRepository
) {


    suspend fun getData(): DataWrapper<Dashdata>{
        val token = secureTokenRepository.getToken()
        token?.let{
            val response = dataRepository.getData(token)
            when (response){
                is ResponseWrapper.Success -> {
                    return DataWrapper.Success(response.data)
                }
                is ResponseWrapper.Error -> {
                    return DataWrapper.Error(response.error)
                }
            }
        } ?: return DataWrapper.Error(AppError.TokenNotFound)
    }
}









