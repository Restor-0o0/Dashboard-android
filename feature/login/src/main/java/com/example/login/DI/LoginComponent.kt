package com.example.login.DI

import dagger.Subcomponent


@Subcomponent(
    modules = [
        AuthApiModule::class,
        AuthRepositoryModule::class
    ]
)
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginComponent
    }
}