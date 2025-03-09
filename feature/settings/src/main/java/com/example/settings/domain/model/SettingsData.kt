package com.example.settings.domain.model

data class SettingsData(
    val settings_data: Array<SettingsData>,
    val drawing_types: Array<DrawingTypes>,
    val types_count: Array<TypesCount>
)