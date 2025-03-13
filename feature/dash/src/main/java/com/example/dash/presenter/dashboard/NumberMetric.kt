package com.example.dash.presenter.dashboard

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun NumberMetric(
    numb: String,
    metric: String
){
    Text(
        text = numb,
        color = Color(MaterialTheme.colorScheme.surface.toArgb()),
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        modifier = Modifier

    )
    Text(
        text = metric,
        color = Color(MaterialTheme.colorScheme.surface.toArgb()),
        fontSize = 30.sp,
        modifier = Modifier

    )
}