package com.example.settings.presenter.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.settings.presenter.SettingsViewModel


@Composable
fun SettingsItemsList(
    navController: NavController,
    viewModel: SettingsViewModel
){

    val settingsList = viewModel.SettingsList.collectAsState()
    Column(
        modifier = Modifier
        //  .clip(RoundedCornerShape(30.dp))
        //  .background(Color(MaterialTheme.colorScheme.primary.toArgb()))
    ) {

        settingsList.value.forEach{ it ->
            SettingsItem(
                settItem = it,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}