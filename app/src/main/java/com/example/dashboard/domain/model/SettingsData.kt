package com.example.dashboard.domain.model

data class SettingsData(
    val settings_data: Array<SettingsData>,
    val drawing_types: Array<DrawingTypes>,
    val types_count: Array<TypesCount>
)