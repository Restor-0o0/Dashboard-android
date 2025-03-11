package com.example.dashboard.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.presenter.Screen

import com.example.dash.presenter.dashboard.DashboardScreen
import com.example.dashboard.presenter.MainActivity
import com.example.login.presenter.login.LoginScreen
import com.example.settings.presenter.settings.SettingsScreen





@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(Screen.Login.route){
            LoginScreen(
                navController,
                (LocalContext.current as MainActivity).viewModelFactory
        ) }
        composable(Screen.Dashboard.route){
            DashboardScreen(
                navController,
                (LocalContext.current as MainActivity).viewModelFactory
            )
        }
        composable(Screen.Settings.route){
            SettingsScreen(
                navController,
                (LocalContext.current as MainActivity).viewModelFactory
            )
        }
    }
}