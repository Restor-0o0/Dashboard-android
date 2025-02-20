package com.example.dashboard.domain.UseCase

import android.media.session.MediaSession.Token
import com.example.dashboard.common.SaveManager
import com.example.dashboard.common.TOKEN_SAVE_NAME
import com.example.dashboard.common.exception.NoInternetException
import com.example.dashboard.common.exception.TokenNotFoundException
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.DataRepository
import com.example.dashboard.domain.model.Dashdata
import javax.inject.Inject
import kotlin.jvm.Throws

class GetDashDataUseCase @Inject constructor(
    private val saveManager: SaveManager,
    private val dataRepository: DataRepository
) {

    @Throws(
        TokenNotFoundException::class,
        NoInternetException::class,
        Exception::class
    )
    suspend fun getData(): Dashdata?{
        val token = saveManager.get(TOKEN_SAVE_NAME)
        token?.let{
            val response = dataRepository.getData(token)
            when (response){
                is ResponseWrapper.Success -> {
                    return response.data
                }
                is ResponseWrapper.Error -> {
                    throw Exception(response.message)
                }
                ResponseWrapper.Loading -> {
                    return  null
                }
            }
        } ?:{
            throw TokenNotFoundException()
        }
        return null
    }
}









