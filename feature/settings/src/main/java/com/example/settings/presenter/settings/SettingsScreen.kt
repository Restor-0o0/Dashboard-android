package com.example.settings.presenter.settings

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.settings.presenter.SettingsViewModel


@Composable
fun SettingsScreen(
    navController: NavController,
    viewModelFactory: ViewModelProvider.Factory
){
    val viewModel: SettingsViewModel = viewModel(factory = viewModelFactory)

    val saveSuccess = viewModel.saveSuccess.collectAsState()

    LaunchedEffect(saveSuccess) {
        if(saveSuccess.value){
            navController.popBackStack()
        }
    }

    Surface() {
        Column(
            modifier = Modifier
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SettingsView(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}



@Preview
@Composable
fun PreviewSettingsScreen(){

}






