package com.example.dashboard.presenter

//import com.patrykandpatrick.vico.compose.chart.Chart
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dashboard.Core.Application
import com.example.core.domain.UseCase.ThemeUseCase
import com.example.dashboard.presenter.navigation.AppNavigation
import com.example.core.presenter.theme.DashboardTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeUseCase: ThemeUseCase

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as Application).appComponent.inject(this)

        setContent {
            val isDarkTheme  = themeUseCase.getTheme().collectAsState(false) //isDark.collectAsState(false);
            DashboardTheme(
                darkTheme = isDarkTheme.value,
                dynamicColor = false
            ){
                AppNavigation()
            }
        }
    }
}
