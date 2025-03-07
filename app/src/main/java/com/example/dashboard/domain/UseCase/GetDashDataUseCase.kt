package com.example.dashboard.domain.UseCase

import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.domain.api.DataRepository
import com.example.dashboard.domain.api.SecureTokenRepository
import com.example.dashboard.domain.model.AppError
import com.example.dashboard.domain.model.DashData
import com.example.dashboard.domain.model.ResponseDashData
import com.example.dashboard.domain.model.DataWrapper
import com.example.dashboard.domain.model.Graphdata
import com.example.dashboard.domain.model.Numbdata
import javax.inject.Inject

class GetDashDataUseCase @Inject constructor(
    private val secureTokenRepository: SecureTokenRepository,
    private val dataRepository: DataRepository
) {


    suspend fun getData(): DataWrapper<DashData>{
        val token = secureTokenRepository.getToken()
        token?.let{
            val response = dataRepository.getData(token)

            when (response){
                is ResponseWrapper.Success -> {
                    return DataWrapper.Success(
                        response.data.let {
                            DashData(
                            login = it.login,
                            numbdata = Numbdata(
                                num_numbs = it.num_numbs,
                                numbs = it.numbs,
                                metrics = it.metrics,
                                head_numb = it.head_numb
                            ),
                            graphdata = Graphdata(
                                num_graphs = it.num_graphs,
                                graph = it.graph,
                                labels = it.labels,
                                head = it.head
                            )
                        )}
                    )
                }
                is ResponseWrapper.Error -> {
                    return DataWrapper.Error(response.error)
                }
            }
        } ?: return DataWrapper.Error(AppError.TokenNotFound)
    }
}









