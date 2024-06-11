package com.example.dashboard.network

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


data class Dashdata(
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


data class Numbdata(
    val num_numbs: Int,
    val numbs:  Array<Float>,
    val metrics: Array<String>,
    val head_numb: Array<String>
)

data class Graphdata(
    val num_graphs: Int,
    val graph: Array<Array<Float>>,
    val labels: Array<Array<String>>,
    val head: Array<String>,

)


data class TypesCount(
    val ID: Int,
    val Name: String,
)

data class DrawingTypes(
    val ID: Int,
    val Name: String,
    val Comment: String,

    )

data class ThemeHolder(
    var theme: Boolean
)


data class SettingsData(
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