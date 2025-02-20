package com.example.dashboard.domain.model

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