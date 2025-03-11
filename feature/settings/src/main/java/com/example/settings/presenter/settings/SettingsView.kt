package com.example.settings.presenter.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.core.presenter.Screen
import com.example.settings.R
import com.example.settings.presenter.SettingsViewModel


@Composable
fun SettingsView(
    navController: NavController,
    viewModel: SettingsViewModel
){
    var visibleState by remember{
        mutableStateOf(false)
    }
    var simb by remember{
        mutableStateOf("⌄")
    }
    val scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            MenuBar(
                navController = navController,
                parentViewModel = viewModel
            )

            Column(

                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .background(
                        Color(MaterialTheme.colorScheme.primary.toArgb()),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clip(RoundedCornerShape(30.dp))
            ) {
                Button(
                    onClick = {
                        visibleState = !visibleState
                        if (simb == "⌄") {
                            simb = "⌃"
                        } else {
                            simb = "⌄"
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        //.background(Color(MaterialTheme.colorScheme.primary.toArgb()))
                        .height(80.dp)
                        .border(
                            width = 2.dp,
                            color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)

                            .clip(RoundedCornerShape(30.dp))
                    )
                    {
                        Text(
                            text = stringResource(R.string.View),
                            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                            fontSize = 20.sp

                        )
                        Text(
                            text = simb,
                            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                            fontSize = 20.sp
                        )//⌃
                    }
                }
                AnimatedVisibility(
                    visible = visibleState,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier
                    //    .background(Color(MaterialTheme.colorScheme.primary.toArgb()))
                ) {
                    SettingsItemsList(
                        navController = navController,
                        viewModel = viewModel
                    )

                }

            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(
                onClick = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Dashboard.route)
                    }
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .border(
                        width = 2.dp,
                        color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clip(RoundedCornerShape(30.dp))
            ){
                Text(
                    text = stringResource(R.string.Exit),
                    fontSize = 20.sp,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb())
                )
            }

        }
    }
}