package com.example.dashboard.domain.model

data class Numbdata( //Числовые данные с серверс
    val num_numbs: Int,
    val numbs:  Array<Float>,
    val metrics: Array<String>,
    val head_numb: Array<String>
)