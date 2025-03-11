package com.example.dash.presenter.dashboard

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLineComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisTickComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.shader.color
import com.patrykandpatrick.vico.core.cartesian.data.AxisValueOverrider
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.LineCartesianLayerModel
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.core.common.component.ShapeComponent
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shader.DynamicShader
import com.patrykandpatrick.vico.core.common.shape.Shape
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun LineChart(
    data: Array<Float>,
    label: Array<String>,
    head: String
) {
    val model = CartesianChartModel(
            LineCartesianLayerModel.build { series(*data) },

            )
    val bottomSpaces = label.map { it.length }.maxOf { it.toInt() }.toInt()
    Column(
        modifier = Modifier
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
            text = head,
            fontSize = 20.sp,
            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 20.dp, end = 20.dp,top=10.dp)
        )
        CartesianChartHost(
            chart =
            rememberCartesianChart(
                rememberLineCartesianLayer(
                    spacing = (bottomSpaces * 16).dp,
                    lines = listOf(
                        rememberLineSpec(
                            shader = DynamicShader.color(Color(MaterialTheme.colorScheme.surface.toArgb()))
                        )
                    ),
                    axisValueOverrider = AxisValueOverrider.fixed(
                        minY = floor(data.minOf { it.toFloat() }.toFloat() * 0.9f).toFloat(),
                        maxY = ceil(data.maxOf { it.toFloat() }.toFloat() * 1.1f).toFloat()
                    )
                ),
                startAxis = rememberStartAxis(

                    label = rememberAxisLabelComponent(
                        color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                        textSize = 20.sp,
                    ),
                    axis = rememberAxisLineComponent(
                        color = Color(MaterialTheme.colorScheme.surface.toArgb()),


                        ),
                    tick = rememberAxisTickComponent(
                        color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                        dynamicShader = null
                    ),


                    ),
                bottomAxis = rememberBottomAxis(
                    label = rememberAxisLabelComponent(
                        color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                        textSize = 20.sp,
                    ),
                    axis = rememberAxisLineComponent(
                        color = Color(MaterialTheme.colorScheme.surface.toArgb()),


                        ),
                    tick = rememberAxisTickComponent(
                        color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                        dynamicShader = null
                    ),
                    valueFormatter = CartesianValueFormatter { x, _, _ ->
                        label[x.toInt() % label.size]
                    },

                    guideline = null,
                    tickLength = 0.dp,
                ),

                ),
            model = model,
            marker = DefaultCartesianMarker(
                indicatorSizeDp = 15f,
                indicator = ShapeComponent(
                    shape = Shape.rounded(10f),
                    color = MaterialTheme.colorScheme.surface.toArgb(),

                    ),
                guideline = LineComponent(
                    color = MaterialTheme.colorScheme.surface.toArgb()
                ),
                labelPosition = DefaultCartesianMarker.LabelPosition.Top,
                label = TextComponent.build {
                    textSizeSp = 20f
                    color = MaterialTheme.colorScheme.surface.toArgb()
                    typeface = Typeface.SERIF

                }
            ),
            modifier = Modifier
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                .height(300.dp)
                .padding(5.dp)
        )

    }

}
