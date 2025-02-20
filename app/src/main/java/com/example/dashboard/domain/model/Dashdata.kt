package com.example.dashboard.domain.model


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