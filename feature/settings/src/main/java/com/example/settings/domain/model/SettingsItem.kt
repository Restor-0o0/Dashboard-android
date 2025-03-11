package com.example.settings.domain.model

data class SettingsItem(
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