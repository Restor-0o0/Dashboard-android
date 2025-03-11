package com.example.login.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.UseCase.ThemeUseCase
import com.example.core.domain.UseCase.TokenUseCase
import com.example.core.domain.model.DataWrapper
import com.example.login.common.MODULE_TAG
import com.example.login.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = "AuthViewModel"

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val themeUseCase: ThemeUseCase,
    private val tokenExistUseCase: TokenUseCase
): ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    var isLoading = MutableStateFlow(false)
    var token = MutableStateFlow("")
    val loginIsSuccess= MutableStateFlow(false)
    var httpErrorRes = MutableStateFlow<Int?>(null)
    val isDarkTheme = themeUseCase.getTheme().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        false
    )

    fun tokenExists(): Boolean {
        Log.d("$MODULE_TAG/$TAG","tokenExists")
        return tokenExistUseCase.tokenExist()
    }



    fun toggleTheme(){
        Log.d("$MODULE_TAG/$TAG","toggleTheme")
        viewModelScope.launch {
            themeUseCase.saveTheme(isDarkTheme.value.not())
        }
    }

    fun setUsername(username: String){
        Log.d("$MODULE_TAG/$TAG","setUsername")
        _username.value = username
    }

    fun setPassword(password: String){
        Log.d("$MODULE_TAG/$TAG","setPassword")
        _password.value = password
    }

    fun performLogin() {
        Log.d("$MODULE_TAG/$TAG","performLogin")
        viewModelScope.launch {
            isLoading.value = true
            val result = authUseCase.login(
                username.value!!,
                password.value!!
            )
            when(result){
                is DataWrapper.Error -> {
                    httpErrorRes.emit(result.error.messageRes!!)
                }
                is DataWrapper.Success -> {
                    Log.e(TAG,result.data.toString())
                    token.value = result.data.auth_token
                    loginIsSuccess.value = true

                }
            }
            isLoading.value = false
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