package com.example.dash.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.UseCase.TokenExistUseCase
import com.example.core.domain.model.AppError
import com.example.core.domain.model.DataWrapper
import com.example.dash.domain.model.Graphdata
import com.example.dash.domain.model.Numbdata
import com.example.dash.domain.usecase.GetDashDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashViewModel @Inject constructor(
    private val getDashDataUseCase: GetDashDataUseCase,
    private val tokenExistUseCase: TokenExistUseCase
) : ViewModel(

) {
    var NumberData = MutableStateFlow(
        Numbdata(num_numbs = 0,
            numbs = arrayOf(),
            metrics = arrayOf(),
            head_numb = arrayOf())
    )

    var GraphicData = MutableStateFlow(
        Graphdata(
            num_graphs = 0,
            graph = arrayOf(arrayOf()),
            labels = arrayOf(arrayOf()),
            head = arrayOf())
    )

    var Login = MutableStateFlow("name")
    var isLoading = MutableStateFlow(true)
    var token = MutableStateFlow("")
    var httpErrorRes = MutableStateFlow<Int?>(null)
    val error = MutableStateFlow<AppError?>(null)
    var initialLoading = MutableStateFlow(true)
    var scrollToRefresh = MutableStateFlow(false)


    fun tokenExists(): Boolean {
        return tokenExistUseCase.tokenExist()
    }

    fun getData() {
        viewModelScope.launch {
            isLoading.emit(true)
            try {
                val data =  getDashDataUseCase.getData()
                when(data){
                    is DataWrapper.Error -> {
                        httpErrorRes.emit(data.error.messageRes!!)
                        error.value = data.error
                    }
                    is DataWrapper.Success -> {
                        NumberData.emit( data.data.numbdata )
                        GraphicData.emit(data.data.graphdata)
                        Login.emit(data.data.login)
                    }

                }

            }
            finally{
                isLoading.emit(false)
            }
        }

    }
    /*if(isLoading && initialLoading) {
        dataresp()
        initialLoading = false
    }
    if(scrollToRefresh && state.isScrollInProgress == true && state.value == 0){
        scrollToRefresh = false
        dataresp()
    }
    if(isLoading == false && state.isScrollInProgress == false && state.value == 0){
        scrollToRefresh = true
    }
    else{
        scrollToRefresh = false
    }*/


}