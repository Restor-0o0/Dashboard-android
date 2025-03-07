package com.example.dashboard.presenter.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard.R
import com.example.dashboard.domain.UseCase.AuthUseCase
import com.example.dashboard.domain.UseCase.ThemeUseCase
import com.example.dashboard.domain.api.LocalDataStore
import com.example.dashboard.domain.model.DataWrapper
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthViewModel @Inject constructor(
   private val dataStore: LocalDataStore,
    private val authUseCase: AuthUseCase,
   private val themeUseCase: ThemeUseCase
): ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    var isLoading = MutableStateFlow(false)
    var token = MutableStateFlow("")
    var httpErrorRes = MutableStateFlow(0)
    val isDarkTheme = themeUseCase.getTheme().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        false
    )

    fun toggleTheme(){
        viewModelScope.launch {
            dataStore.save(!isDarkTheme.value)
        }
    }

    fun setUsername(username: String){
        _username.value = username
    }

    fun setPassword(password: String){
        _password.value = password
    }

    fun performLogin() {
        isLoading.value = true
        viewModelScope.launch {

            val result = authUseCase.login(
                username.value!!,
                password.value!!
            )
            when(result){
                is DataWrapper.Error -> {
                    httpErrorRes.emit(result.error.messageRes!!)
                }
                is DataWrapper.Success -> {
                    token.emit(result.data.auth_token)
                }
            }
        }
    }
    /*if(theme.value == true)
    {
        simb = "☾"
    }
    else{
        simb = "☀"
    }*/
}