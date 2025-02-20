package com.example.dashboard.data.reposetory

import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.User
import com.example.dashboard.domain.api.AuthReposetory
import javax.inject.Inject

class AuthReposetoryImpl @Inject constructor(
    private val api: AuthService
): AuthReposetory {
    override suspend fun login(username: String, password: String): ResponseWrapper<User> {
        return try{
            val response = api.login(
                username,
                password
            )
            if(response.isSuccessful){
                return ResponseWrapper.Success(response.body()!!)
            }else{
                    return ResponseWrapper.Error(response.message())
            }
        }catch (e: Exception){
            return ResponseWrapper.Error(e.message.toString())
        }
    }
}