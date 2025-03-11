package com.example.login.DI

import dagger.Module

@Module(
    includes = [
        AuthApiModule::class,
        AuthRepositoryModule::class,
        AuthViewModelModule::class
    ]
)
class LoginModule {
}