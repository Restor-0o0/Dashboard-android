package com.example.dash.presenter.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NumbContainer(
    numb: Float,
    metric: String,
    head_numb: String
){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(195.dp)
            .height(190.dp)
            .padding(10.dp)
            .background(
                Color(MaterialTheme.colorScheme.primary.toArgb()),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 2.dp,
                color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                shape = RoundedCornerShape(20.dp)
            )

    ){
        Text(
            text = head_numb,
            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(top = 10.dp)

        )

            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 25.dp)
                    .wrapContentSize()

            ) {
                NumberMetric(
                    numb = numb.toString() ,
                    metric = " $metric"
                )
            }

    }
}
