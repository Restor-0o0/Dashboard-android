package com.example.dashboard.presenter.View.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount


@Composable
fun SettingsItemsList(
    settingsList: MutableList<SettingsData>,
    drawingTypesList: MutableList<DrawingTypes>,
    typesCountList: MutableList<TypesCount>
){
    Column(
        modifier = Modifier
        //  .clip(RoundedCornerShape(30.dp))
        //  .background(Color(MaterialTheme.colorScheme.primary.toArgb()))
    ) {
        settingsList.forEach{ it ->
            SettingsItem(
                settItem = it,
                drawsList = drawingTypesList,
                typesList = typesCountList
            )
        }
    }
}