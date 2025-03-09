package com.example.dashboard.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.dashboard.presenter.dashboard.DashboardScreen
import com.example.login.presenter.login.LoginScreen
import com.example.settings.presenter.settings.SettingsScreen


sealed class Screen(val route:String){
    object Login: Screen("login")
    object Dashboard: Screen("dashboard")
    object Settings: Screen("settings")
}


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable(Screen.Login.route){ LoginScreen(navController) }
        composable(Screen.Dashboard.route){
            DashboardScreen(
                navController
            )
        }
        composable(Screen.Settings.route){
            SettingsScreen(
                navController
            )
        }
    }
}