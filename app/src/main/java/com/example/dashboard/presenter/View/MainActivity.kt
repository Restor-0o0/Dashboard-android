package com.example.dashboard.presenter.View

//import com.patrykandpatrick.vico.compose.chart.Chart
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import com.example.dashboard.Core.Application
import com.example.dashboard.domain.UseCase.ThemeUseCase
import com.example.dashboard.presenter.theme.DashboardTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


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
