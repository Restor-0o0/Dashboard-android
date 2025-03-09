package com.example.dashboard.presenter.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.dashboard.presenter.View.dashboard.DashboardScreen
import com.example.dashboard.presenter.View.login.LoginScreen
import com.example.dashboard.presenter.View.settings.SettingsScreen



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
        composable(Screen.Dashboard.route){ DashboardScreen(navController) }
        composable(Screen.Settings.route){ SettingsScreen(navController) }
    }
}