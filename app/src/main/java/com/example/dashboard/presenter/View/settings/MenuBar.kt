package com.example.dashboard.presenter.View.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.dashboard.R
import com.example.dashboard.domain.model.SettingsData
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dashboard.presenter.vm.SettingsViewModel


@Composable
fun MenuBar(
    navController: NavController
    ){
    val viewModel: SettingsViewModel = viewModel()
    val imageSave = painterResource(R.drawable.save)
    val imageBack = painterResource(R.drawable.back)
    var simb = viewModel.`

    if(theme.value == true)
    {
        simb = "☾"
    }
    else{
        simb = "☀"
    }
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    .width(60.dp)
                    .height(60.dp)

            )
            {
                Icon(
                    painter = imageBack,
                    contentDescription = "menu",
                    tint = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Button(onClick = {
                тему менят
            },

                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)

            ) {

                Text(
                    text = simb,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    fontSize = 20.sp
                )
            }
            IconButton(
                onClick = {
                    dataSend()
                },
                modifier = Modifier
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    .width(60.dp)
                    .height(60.dp)

            )
            {
                Icon(
                    painter = imageSave,
                    contentDescription = "menu",
                    tint = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Row(
            modifier = Modifier
                .background(Color(MaterialTheme.colorScheme.secondary.toArgb()))
                .fillMaxWidth()
                .height(2.dp)
        ) {}
    }
}