package com.example.dashboard.data.model

import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount

data class SettingsResponse(
    val settings_data: Array<SettingsData>,
    val drawing_types: Array<DrawingTypes>,
    val types_count: Array<TypesCount>
)