package com.example.dashboard.presenter.View.navigation

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Dashboard: Routes("dashboard")
    object Settings: Routes("settings")
}