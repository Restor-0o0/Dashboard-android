package com.example.dash.presenter.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dash.presenter.DashViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(
    navController: NavController,
    viewModel: DashViewModel
){
    val login = viewModel.Login.collectAsState()
    val state = rememberScrollState()
    val isLoading = viewModel.isLoading.collectAsState()
    val NumberData = viewModel.NumberData.collectAsState()
    val GraphicData = viewModel.GraphicData.collectAsState()
    val httpErrorRes = viewModel.httpErrorRes.collectAsState()
    val httpError = if(httpErrorRes.value != null)LocalContext.current.getString(httpErrorRes.value!!) else ""
Column(
    modifier = Modifier
        .background(Color(MaterialTheme.colorScheme.background.toArgb()))
        .fillMaxSize()
) {
    InfoBar(
        login = login.value,
        navController= navController,
        viewModel = viewModel
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(state)
            .background(Color(MaterialTheme.colorScheme.background.toArgb()))
            .fillMaxSize()


    ) {
        if (isLoading.value) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        else if(NumberData.value.num_numbs == 0 && GraphicData.value.num_graphs == 0){
            Text(
                text = httpError,
                fontSize = 25.sp,
                color = Color(MaterialTheme.colorScheme.surface.toArgb())
            )
        }
        else if(NumberData.value.num_numbs > 0 || GraphicData.value.num_graphs > 0){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier

                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    .fillMaxSize()
                    .fillMaxHeight()

            ) {


                for (index in NumberData.value.head_numb.indices step 2) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(MaterialTheme.colorScheme.background.toArgb()))

                    ) {
                        Log.e("Fuck", NumberData.value.numbs[index].toString())
                        if (index + 1 < NumberData.value.head_numb.size) {
                            NumbContainer(
                                numb = NumberData.value.numbs[index],
                                metric = NumberData.value.metrics[index],
                                head_numb = NumberData.value.head_numb[index]
                            )
                            NumbContainer(
                                numb = NumberData.value.numbs[index + 1],
                                metric = NumberData.value.metrics[index + 1],
                                head_numb = NumberData.value.head_numb[index + 1]
                            )
                        } else {

                            NumbContainer(
                                numb = NumberData.value.numbs[index],
                                metric = NumberData.value.metrics[index],
                                head_numb = NumberData.value.head_numb[index]
                            )
                        }
                    }


                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))

            ) {

                for (index in GraphicData.value.head.indices) {
                    Log.e("Index", index.toString())
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(MaterialTheme.colorScheme.background.toArgb()))

                    ) {

                        LineChart(
                            data = GraphicData.value.graph[index],
                            label = GraphicData.value.labels[index],
                            head = GraphicData.value.head[index]
                        )

                    }


                }


            }
        }

    }
}



}
