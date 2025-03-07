package com.example.dashboard.presenter.View.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dashboard.presenter.theme.DashboardTheme
import com.example.dashboard.presenter.vm.AuthViewModel



@Composable
fun LoginScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LoginUI(navController)
    }
}


@Composable
fun LoginUI(
    navController: NavController
){
    val viewModel: AuthViewModel = viewModel()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val focusManager = LocalFocusManager.current
    val isLoading = viewModel.isLoading.collectAsState()
    val httpErrorRes = viewModel.httpErrorRes.collectAsState()
    val httpError = LocalContext.current.resources.getString(httpErrorRes.value)
    var simb = if(viewModel.isDarkTheme.collectAsState().value) "☾" else "☀"

    Column {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Button(
                onClick = {
                    viewModel.toggleTheme()
                          },
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)

            ) {
                Text(
                    text = simb,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    fontSize = 20.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Вход",
                fontSize = 24.sp,
                color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.setUsername(it) },
                label = {
                    Text(
                        text = "Username",
                        color = Color(MaterialTheme.colorScheme.surface.toArgb())
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.surface,

                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.setPassword(it) },
                label = {
                    Text(
                        text = "Пароль",
                        color = Color(MaterialTheme.colorScheme.surface.toArgb())
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.performLogin() }
                ),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.surface,

                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = { viewModel.performLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text(
                        text = "Войти",
                        color = Color(MaterialTheme.colorScheme.surface.toArgb())
                    )
                }
            }
            Text(
                text = httpError,
                fontSize = 10.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start=5.dp,end=5.dp)
            )
        }

    }
}

/*
@Preview(
    showBackground = true,
)
@Composable
fun Prew()
{
    LoginScreen()
}*/