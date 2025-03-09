package com.example.login.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.UseCase.ThemeUseCase
import com.example.core.domain.api.LocalDataStore
import com.example.core.domain.model.DataWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val dataStore: com.example.core.domain.api.LocalDataStore,
    private val authUseCase: com.example.login.domain.usecase.AuthUseCase,
    private val themeUseCase: com.example.core.domain.UseCase.ThemeUseCase
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