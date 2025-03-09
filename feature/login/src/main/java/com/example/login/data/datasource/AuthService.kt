package com.example.login.data.datasource

import com.example.core.utils.AUTH_PATH
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService { //Интерфейс для авторизации
    @FormUrlEncoded
    @POST(AUTH_PATH)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): com.example.login.data.model.User
}