package com.example.dashboard.presenter.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.dashboard.presenter.View.dashboard.DashboardScreen
import com.example.dashboard.presenter.View.login.LoginScreen
import com.example.dashboard.presenter.View.settings.SettingsScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){ LoginScreen(navController) }
        composable("dashboard"){ DashboardScreen(navController) }
        composable("settings"){ SettingsScreen(navController) }
    }
}