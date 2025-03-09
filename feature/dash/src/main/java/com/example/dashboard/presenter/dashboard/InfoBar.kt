package com.example.dashboard.presenter.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dashboard.R


@Composable
fun InfoBar(
    login: String,
    navController: NavController
){
    val image = painterResource(R.drawable.baseline_menu_24)
    Column {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))

        ) {
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    .width(60.dp)
                    .height(60.dp)

            )
            {
                Icon(
                    painter = image,
                    contentDescription = "menu",
                    tint = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Text(
                text = login,
                fontSize = 30.sp,
                color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                modifier = Modifier
                    .padding(end = 20.dp)
            )
        }
        Row(
            modifier = Modifier
                .background(Color(MaterialTheme.colorScheme.secondary.toArgb()))
                .fillMaxWidth()
                .height(2.dp)
        ) {}
    }

}
