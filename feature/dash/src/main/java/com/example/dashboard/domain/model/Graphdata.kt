package com.example.dashboard.domain.model

data class Graphdata( //Данные с сервера для графика
    val num_graphs: Int,
    val graph: Array<Array<Float>>,
    val labels: Array<Array<String>>,
    val head: Array<String>,

    )