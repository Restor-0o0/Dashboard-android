package com.example.settings.domain.model

data class SettingsData(
    val settings_data: List<SettingsItem>,
    val drawing_types: List<DrawingTypes>,
    val types_count: List<TypesCount>
)
