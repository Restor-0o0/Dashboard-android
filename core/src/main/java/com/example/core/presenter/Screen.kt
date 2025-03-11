package com.example.core.presenter

sealed class Screen(val route:String){
    object Login: Screen("login")
    object Dashboard: Screen("dashboard")
    object Settings: Screen("settings")
}