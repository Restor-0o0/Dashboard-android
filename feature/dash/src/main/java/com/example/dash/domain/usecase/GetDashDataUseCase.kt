package com.example.dash.domain.usecase

import android.util.Log
import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.domain.model.AppError
import com.example.core.domain.model.DataWrapper
import com.example.dash.common.MODULE_TAG
import com.example.dash.domain.api.DataRepository

import com.example.dash.domain.model.DashData

import javax.inject.Inject

private val TAG = "GetDashDataUseCase"

class GetDashDataUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val dataRepository: DataRepository
) {


    suspend fun getData(): DataWrapper<DashData> {

        val token = secureTokenRepository.getToken()
        Log.d("$MODULE_TAG/$TAG","getData " + token)
        token?.let{
            val response = dataRepository.getData(token.toString())
            Log.d("$MODULE_TAG/$TAG","getData " + "response")
            when (response){
                is ResponseWrapper.Success -> {
                    return DataWrapper.Success(
                        response.data.let {
                            DashData(
                                login = it.login,
                                numbdata = com.example.dash.domain.model.Numbdata(
                                    num_numbs = it.num_numbs,
                                    numbs = it.numbs,
                                    metrics = it.metrics,
                                    head_numb = it.head_numb
                                ),
                                graphdata = com.example.dash.domain.model.Graphdata(
                                    num_graphs = it.num_graphs,
                                    graph = it.graph,
                                    labels = it.labels,
                                    head = it.head
                                )
                            )
                        }
                    )
                }
                is ResponseWrapper.Error -> {
                    return DataWrapper.Error(response.error)
                }
            }
        } ?: return DataWrapper.Error(AppError.TokenNotFound)
    }
}









