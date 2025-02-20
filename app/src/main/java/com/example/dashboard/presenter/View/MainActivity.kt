package com.example.dashboard.presenter.View

//import com.patrykandpatrick.vico.compose.chart.Chart
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.R
import com.example.dashboard.domain.model.Graphdata
import com.example.dashboard.domain.model.Numbdata
import com.example.dashboard.data.api.RetrofitInstance
import com.example.dashboard.common.SaveManager
import com.example.dashboard.presenter.theme.DashboardTheme
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
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.math.ceil
import kotlin.math.floor


class MainActivity : ComponentActivity() {
    open class SettingsCallback {
        open fun toSettings() {}
        open fun toLogin() {}
    }
    /*private val settingsCallback = object : SettingsCallback() {
        override fun toSettings() {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
        override fun toLogin() {
            SaveManager.clear(this@MainActivity,"token")
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }*/
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            /*setContent {
                var Theme = remember{ mutableStateOf(true) }
                if(SaveManager.get(this@MainActivity,"theme")!= null)
                {
                    Theme.value = SaveManager.get(this@MainActivity,"theme").toBoolean()
                }
                else{
                    SaveManager.save(this@MainActivity,"theme",Theme.toString())
                }
                DashboardTheme(
                    darkTheme = Theme.value,
                    dynamicColor = false
                ) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxHeight()
                            .background(Color(MaterialTheme.colorScheme.background.toArgb())),

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(Color(MaterialTheme.colorScheme.background.toArgb())),

                            ) {

                            ViewData(
                                cont = this@MainActivity,
                                settingsCallback = settingsCallback,

                                )
                        }



                    }
                }
            }*/
        }
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewData(
    cont: Context,
    settingsCallback: MainActivity.SettingsCallback
    ) {


    var NumberData by remember{ mutableStateOf(
        Numbdata(num_numbs = 0,
        numbs = arrayOf(),
        metrics = arrayOf(),
        head_numb = arrayOf())
    )
    }
    var GraphicData by remember{ mutableStateOf(
        Graphdata(
        num_graphs = 0,
        graph = arrayOf(arrayOf()),
        labels = arrayOf(arrayOf()),
        head = arrayOf())
    )
    }
    var Login by remember{ mutableStateOf("name") }
    var isLoading by remember{ mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    var state = rememberScrollState()
    var httpError by remember{mutableStateOf("")}
    var initialLoading by remember{ mutableStateOf(true) }
    var scrollToRefresh by remember{ mutableStateOf(false) }
    fun dataresp() {
        isLoading = true
        scope.launch {
            try {
                NumberData = Numbdata(num_numbs = 0,
                    numbs = arrayOf(),
                    metrics = arrayOf(),
                    head_numb = arrayOf())
                GraphicData = Graphdata(
                    num_graphs = 0,
                    graph = arrayOf(arrayOf()),
                    labels = arrayOf(arrayOf()),
                    head = arrayOf())
                httpError = "Нет данных"
                val DataDash = RetrofitInstance.dataDashService.getData(token ="Token " + SaveManager.get(context = cont,"token"))
                Login = DataDash.login
                NumberData = Numbdata(
                    num_numbs = DataDash.num_numbs,
                    numbs = DataDash.numbs,
                    metrics = DataDash.metrics,
                    head_numb = DataDash.head_numb
                )
                GraphicData = Graphdata(
                    num_graphs = DataDash.num_graphs,
                    graph = DataDash.graph,
                    labels = DataDash.labels,
                    head = DataDash.head,
                )
            } catch (e: Exception){
                httpError = "Ошибка сервера"
            }
            catch (e: HttpException) {
                if(e.code() == 401)
                {
                    SaveManager.save(
                        context = cont,
                        key = "HttpError",
                        value = cont.getString(R.string.Unauthorized))
                    settingsCallback.toLogin()
                }


            }

            finally{
                isLoading = false
                scope.coroutineContext.job.join()
            }
        }

    }
    if(isLoading && initialLoading) {
        dataresp()
        initialLoading = false
    }
    if(scrollToRefresh && state.isScrollInProgress == true && state.value == 0){
        scrollToRefresh = false
        dataresp()
    }
    if(isLoading == false && state.isScrollInProgress == false && state.value == 0){
        scrollToRefresh = true
    }
    else{
        scrollToRefresh = false
    }


    InfoBar(
        login = Login,
        settingsCallback = settingsCallback
    )


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(state)
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                .fillMaxSize()


        ) {

            if (isLoading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            else if(NumberData.num_numbs == 0 && GraphicData.num_graphs == 0){
                Text(
                    text = httpError,
                    fontSize = 25.sp,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb())
                )
            }
            else if(NumberData.num_numbs > 0 || GraphicData.num_graphs > 0){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier

                        .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                        .fillMaxSize()
                        .fillMaxHeight()

                ) {


                    for (index in NumberData.head_numb.indices step 2) {

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color(MaterialTheme.colorScheme.background.toArgb()))

                        ) {
                            Log.e("Fuck", NumberData.numbs[index].toString())
                            if (index + 1 < NumberData.head_numb.size) {
                                NumbContainer(
                                    numb = NumberData.numbs[index],
                                    metric = NumberData.metrics[index],
                                    head_numb = NumberData.head_numb[index]
                                )
                                NumbContainer(
                                    numb = NumberData.numbs[index + 1],
                                    metric = NumberData.metrics[index + 1],
                                    head_numb = NumberData.head_numb[index + 1]
                                )
                            } else {

                                NumbContainer(
                                    numb = NumberData.numbs[index],
                                    metric = NumberData.metrics[index],
                                    head_numb = NumberData.head_numb[index]
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

                    for (index in GraphicData.head.indices) {
                        Log.e("Index", index.toString())
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(MaterialTheme.colorScheme.background.toArgb()))

                        ) {

                            LineChart(
                                data = GraphicData.graph[index],
                                label = GraphicData.labels[index],
                                head = GraphicData.head[index]
                            )

                        }


                    }


                }
            }

        }


}


@Composable
fun InfoBar(
    settingsCallback: MainActivity.SettingsCallback?,
    login: String,
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
                settingsCallback?.toSettings()
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
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 25.dp)
        ) {
            Text(
                text = numb.toString(),
                color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                modifier = Modifier

            )
            Text(
                text = " " + metric,
                color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                fontSize = 40.sp,
                modifier = Modifier

            )
        }
    }
}


@Composable
fun LineChart(
    data: Array<Float>,
    label: Array<String>,
    head: String
) {
    val model =
        CartesianChartModel(
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

val data3: Array<Float> = arrayOf(
1.5f,3f,3f,4f,5f,6f,7f,1f,2f,3f,4f,5f,6f,7f
)





@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    DashboardTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Column {
            InfoBar(
                settingsCallback = null,
                login = "rest"
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
*/