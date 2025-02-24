package com.example.dashboard.data.datasource

import com.example.dashboard.common.AUTH_PATH
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService { //Интерфейс для авторизации
    @FormUrlEncoded
    @POST(AUTH_PATH)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): User
}