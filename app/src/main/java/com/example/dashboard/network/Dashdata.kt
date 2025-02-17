package com.example.dashboard.network

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


data class Dashdata( //Для получения данных отрисовки с сервера
    val login: String,
    val num_graphs: Int,
    val graph: Array<Array<Float>>,
    val labels: Array<Array<String>>,
    val head: Array<String>,
    val num_numbs: Int,
    val numbs:  Array<Float>,
    val metrics: Array<String>,
    val head_numb: Array<String>
)


data class Numbdata( //Числовые данные с серверс
    val num_numbs: Int,
    val numbs:  Array<Float>,
    val metrics: Array<String>,
    val head_numb: Array<String>
)

data class Graphdata( //Данные с сервера для графика
    val num_graphs: Int,
    val graph: Array<Array<Float>>,
    val labels: Array<Array<String>>,
    val head: Array<String>,

)


data class TypesCount( //Данные для настроек (типы группировки)
    val ID: Int,
    val Name: String,
)

data class DrawingTypes( //Данные для настроек (типы отрисовки)
    val ID: Int,
    val Name: String,
    val Comment: String,

    )

data class ThemeHolder(
    var theme: Boolean
)


data class SettingsData( //Данные для настроек
    val ID: Int,
    var CountVals: Int,
    var Priority: Int,
    val User: Int,
    val Group: Int,
    var TypeCount: Int,
    var DrawingType: Int,
    val Name: String,
    var Active: Boolean
)

data class SettingsSendData(
    val ID: Int,
    var CountVals: Int,
    var Priority: Int,
    val User: Int,
    val Group: Int,
    var TypeCount: Int,
    var DrawingType: Int,
)