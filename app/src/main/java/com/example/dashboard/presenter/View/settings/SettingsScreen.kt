package com.example.dashboard.presenter.View.settings

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount

@Composable
fun SettingsScreen(navController: NavController) {
    Surface() {
        Column(
            modifier = Modifier
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SettingsView(
                theme = Theme
            )
        }
    }
}







