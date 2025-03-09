package com.example.dashboard.presenter

//import com.patrykandpatrick.vico.compose.chart.Chart
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.example.dashboard.Core.Application
import com.example.core.domain.UseCase.ThemeUseCase
import com.example.dashboard.presenter.navigation.AppNavigation
import com.example.core.presenter.theme.DashboardTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MainActivity : ComponentActivity() {


    lateinit var themeUseCase: ThemeUseCase

    private lateinit var isDark: Flow<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as Application).appComponent.inject(this)
        isDark = flow{
            themeUseCase.getTheme()
        }
        setContent {
            val isDarkTheme  = isDark.collectAsState(false);
            DashboardTheme(
                darkTheme = isDarkTheme.value,
                dynamicColor = false
            ){
                AppNavigation()
            }
        }
    }
}
