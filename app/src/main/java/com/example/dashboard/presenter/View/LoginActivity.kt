package com.example.dashboard.presenter.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dashboard.R
import com.example.dashboard.data.api.RetrofitInstance
import com.example.dashboard.common.SaveManager
import com.example.dashboard.presenter.theme.DashboardTheme
import kotlinx.coroutines.launch
import retrofit2.HttpException


class LoginActivity : ComponentActivity() {
    open class LoginCallback {
        open fun onLoginSuccess(token: String) {}
    }
    private val loginCallback = object : LoginCallback() {
        override fun onLoginSuccess(token: String) {
            Log.e("DEBUGAAAA","Perehodim")
            //SaveManager.save(this@LoginActivity,"token", token)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*installSplashScreen()
        setContent {
            var Theme = remember{ mutableStateOf(true) }
            if(SaveManager.get(this@LoginActivity,"theme")!= null)
            {
                Theme.value = SaveManager.get(this@LoginActivity,"theme").toBoolean()
            }
            else{
                SaveManager.save(this@LoginActivity,"theme",Theme.toString())
            }
            DashboardTheme(
                darkTheme = Theme.value,
                dynamicColor = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //SaveManager.clear(this,"token")
                    if(SaveManager.get(this,"token") != null)
                    {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }

                    LoginScreen(
                        logcallback = loginCallback,
                        cont = this,
                        theme = Theme
                    )
                }
            }
        }*/
    }

}



/*
@Composable
fun LoginScreen(
    cont: Context,
    logcallback: LoginActivity.LoginCallback,
    theme: MutableState<Boolean>,
){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var token by remember { mutableStateOf("") }
    var simb by remember { mutableStateOf("") }
    var httpError by remember{mutableStateOf("")}
    if(SaveManager.get(context = cont,key="HttpError") != null){
        httpError = SaveManager.get(context = cont,key="HttpError").toString()
    }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    fun performLogin() {
        isLoading = true
        scope.launch {
            try {
                Log.e("DEBUGAAAA","Poprosili")
                val user = RetrofitInstance.authService.login(username, password)
                Log.e("DEBUGAAAA","Poluchili")
                token = user.auth_token
                if(user.auth_token != ""){

                    SaveManager.save(context = cont,key="HttpError", value = "")
                    logcallback.onLoginSuccess(
                        token = user.auth_token
                    )
                    Log.e("DEBUGAAAA","sohranili")
                }
            } catch (e: HttpException) {
                if(e.code() == 401)
                {
                    httpError= cont.getString(R.string.Unauthorized)
                }
                else if(e.code()% 500 == 1){
                    httpError = cont.getString(R.string.ServerError)
                }
            } finally {
                isLoading = false
            }


        }
    }


    if(theme.value == true)
    {
        simb = "☾"
    }
    else{
        simb = "☀"
    }
    Column {


        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Button(onClick = {
                theme.value = theme.value.not()
                SaveManager.save(cont, "theme", theme.value.toString())
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
                onValueChange = { username = it },
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
                onValueChange = { password = it },
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
                    onDone = { performLogin() }
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
                onClick = { performLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isLoading) {
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
}*/

/*
@Preview(
    showBackground = true,
)
@Composable
fun Prew()
{
    LoginScreen()
}*/