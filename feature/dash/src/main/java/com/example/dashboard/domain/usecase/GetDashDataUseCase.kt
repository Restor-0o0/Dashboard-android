package com.example.dashboard.domain.usecase

import com.example.core.data.model.ResponseWrapper
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.domain.model.AppError
import com.example.core.domain.model.DataWrapper

import com.example.dashboard.domain.model.DashData
import javax.inject.Inject

class GetDashDataUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val dataRepository: com.example.dashboard.domain.api.DataRepository
) {


    suspend fun getData(): DataWrapper<DashData> {
        val token = secureTokenRepository.getToken()
        token?.let{
            val response = dataRepository.getData(token)

            when (response){
                is ResponseWrapper.Success -> {
                    return DataWrapper.Success(
                        response.data.let {
                            com.example.dashboard.domain.model.DashData(
                                login = it.login,
                                numbdata = com.example.dashboard.domain.model.Numbdata(
                                    num_numbs = it.num_numbs,
                                    numbs = it.numbs,
                                    metrics = it.metrics,
                                    head_numb = it.head_numb
                                ),
                                graphdata = com.example.dashboard.domain.model.Graphdata(
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









