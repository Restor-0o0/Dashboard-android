@file:Suppress("UNREACHABLE_CODE")

package com.example.dashboard.presenter.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.presenter.theme.DashboardTheme


@Composable
fun DashboardScreen(navController: NavController){
    Surface(

    ) {
        DashboardView(navController)
    }
}










@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    val data3: Array<Float> = arrayOf(
        1.5f,3f,3f,4f,5f,6f,7f,1f,2f,3f,4f,5f,6f,7f
    )
    DashboardTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Column {
            InfoBar(
                login = "rest",
                navController = NavController(
                    context = LocalContext.current
                )
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
            ){
                NumbContainer(numb = 20.2F, metric = "C", head_numb = "Температура коридор")
                NumbContainer(numb = 20.2F, metric = "C", head_numb = "Температура")

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
            ) {

                LineChart(data3,arrayOf("пн", "вт", "ср", "чт", "пт", "сб", "вс"),head = "Температура коридор")



            }
        }
    }
}
