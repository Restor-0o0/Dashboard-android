package com.example.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import android.window.SplashScreenView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.network.AuthMan
import com.example.dashboard.network.DrawingTypes
import com.example.dashboard.network.RetrofitInstance
import com.example.dashboard.network.SaveManager
import com.example.dashboard.network.SettingsData
import com.example.dashboard.network.SettingsSendData
import com.example.dashboard.network.TypesCount
import com.example.dashboard.ui.theme.DashboardTheme
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SettingsActivity : ComponentActivity() {
    open class SettingsCallback {
        open fun onSettingsSave() {}
        open fun onSettingsBack() {}
        open fun toLogin() {}
    }
    private val settingsCallback = object : SettingsCallback() {
        override fun onSettingsSave() {

            val intent = Intent(this@SettingsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        override fun onSettingsBack() {
            val intent = Intent(this@SettingsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        override fun toLogin() {
            val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            var Theme = remember{ mutableStateOf(true) }
            if(SaveManager.get(this@SettingsActivity,"theme")!= null)
            {
                Theme.value = SaveManager.get(this@SettingsActivity,"theme").toBoolean()
            }
            else{
                SaveManager.save(this@SettingsActivity,"theme",Theme.toString())
            }

            DashboardTheme(
                darkTheme = Theme.value,
                dynamicColor = false
            ) {
                Surface(

                ) {
                    Column(
                        modifier = Modifier
                            .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())

                    ) {

                        MenuView(
                            cont = this@SettingsActivity,
                            settingsCallback = settingsCallback,
                            theme = Theme
                        )

                    }




                }
            }

        }
    }
}



@Composable
fun MenuBar(
    cont: Context,
    settingsCallback: SettingsActivity.SettingsCallback,
    settingsList: MutableList<SettingsData>,
    theme: MutableState<Boolean>,

    ){
    val imageSave = painterResource(R.drawable.save)
    val imageBack = painterResource(R.drawable.back)
    val scope = rememberCoroutineScope()
    var simb by remember { mutableStateOf("") }

    fun dataSend(){
        scope.launch {
            try {
                /*var SendList = mutableListOf<SettingsSendData>()
                for(ind in settingsList)
                {
                    SendList.add(SettingsSendData(
                        ID = ind.ID,
                        CountVals = ind.CountVals,
                        Priority = ind.Priority,
                        User = ind.User,
                        Group = ind.Group,
                        TypeCount = ind.TypeCount,
                        DrawingType = ind.DrawingType,
                    ))
                }*/
                RetrofitInstance.dataDashService.setSettingsData(
                    token ="Token "+ SaveManager.get(context = cont,"token"),
                    data = settingsList
                )

            }
            catch (e : Exception)
            {
                Log.e("SizeSett",e.toString()+" "+ AuthMan.getToken(context = cont))
            }
            finally {
                settingsCallback?.onSettingsBack()
            }
            scope.cancel()
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(MaterialTheme.colorScheme.background.toArgb()))
        ) {

            IconButton(
                onClick = {
                    settingsCallback?.onSettingsSave()
                },
                modifier = Modifier
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    .width(60.dp)
                    .height(60.dp)

            )
            {
                Icon(
                    painter = imageBack,
                    contentDescription = "menu",
                    tint = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
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
            IconButton(
                onClick = {
                    dataSend()
                          },
                modifier = Modifier
                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    .width(60.dp)
                    .height(60.dp)

            )
            {
                Icon(
                    painter = imageSave,
                    contentDescription = "menu",
                    tint = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Row(
            modifier = Modifier
                .background(Color(MaterialTheme.colorScheme.secondary.toArgb()))
                .fillMaxWidth()
                .height(2.dp)
        ) {}
    }
}


@Composable
fun MenuView(
    cont: Context,
    settingsCallback: SettingsActivity.SettingsCallback,
    theme: MutableState<Boolean>,

    ){

    var SettingsList = remember{ mutableListOf<SettingsData>() }
    var visibleState by remember{
        mutableStateOf(false)
    }
    var simb by remember{
        mutableStateOf("⌄")
    }
    var TypesCountList = remember{ mutableListOf<TypesCount>() }
    var DrawingTypesList = remember{ mutableListOf<DrawingTypes>() }
    val scope = rememberCoroutineScope()
    fun dataSett(){
        scope.launch {
            try {
                var localList1 = RetrofitInstance.dataDashService.getSettingsData(token ="Token "+ SaveManager.get(context = cont,"token"))
                localList1.forEach(){
                    SettingsList.add(it)
                }
                var localList2 = RetrofitInstance.dataDashService.getTypesCount(token ="Token "+ SaveManager.get(context = cont,"token"))
                localList2.forEach(){
                    TypesCountList.add(it)
                }
                var locallist3 = RetrofitInstance.dataDashService.getDrawingTypes(token ="Token "+ SaveManager.get(context = cont,"token"))
                locallist3.forEach(){
                    DrawingTypesList.add(it)
                }
                Log.e("SizeSett",SettingsList.size.toString()+" "+ AuthMan.getToken(context = cont))
            }
            catch (e : Exception)
            {
                Log.e("SizeSett",e.toString()+" "+ AuthMan.getToken(context = cont))
            }
            scope.cancel()
        }

    }
    dataSett()
    Column(
        verticalArrangement = Arrangement.SpaceBetween
            ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            MenuBar(
                cont = cont,
                settingsCallback = settingsCallback,
                settingsList = SettingsList,
                theme = theme
            )

            Column(

                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .background(
                        Color(MaterialTheme.colorScheme.primary.toArgb()),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clip(RoundedCornerShape(30.dp))
            ) {


                Button(
                    onClick = {
                        visibleState = !visibleState
                        if (simb == "⌄") {
                            simb = "⌃"
                        } else {
                            simb = "⌄"
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        //.background(Color(MaterialTheme.colorScheme.primary.toArgb()))
                        .height(80.dp)
                        .border(
                            width = 2.dp,
                            color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)

                            .clip(RoundedCornerShape(30.dp))
                    )
                    {
                        Text(
                            text = stringResource(R.string.View),
                            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                            fontSize = 20.sp

                        )
                        Text(
                            text = simb,
                            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                            fontSize = 20.sp
                        )//⌃
                    }
                }
                AnimatedVisibility(
                    visible = visibleState,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier
                    //    .background(Color(MaterialTheme.colorScheme.primary.toArgb()))
                ) {
                    SettingsView(
                        cont = cont,
                        settingsList = SettingsList,
                        drawingTypesList = DrawingTypesList,
                        typesCountList = TypesCountList,
                    )

                }

            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(
                onClick = {
                    SaveManager.clear(context = cont, key = "token")
                    settingsCallback.toLogin()
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .border(
                        width = 2.dp,
                        color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clip(RoundedCornerShape(30.dp))
            ){
                Text(
                    text = stringResource(R.string.Exit),
                    fontSize = 20.sp,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb())
                )
            }

        }
    }
}


@Composable
fun SettingsView(
    cont: Context,
    settingsList: MutableList<SettingsData>,
    drawingTypesList: MutableList<DrawingTypes>,
    typesCountList: MutableList<TypesCount>
)
{


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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsItem(
    settItem: SettingsData,
    drawsList: MutableList<DrawingTypes>,
    typesList: MutableList<TypesCount>
) {
    var expanded by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(settItem.Active) }
    val DrawMap = drawsList.associateBy( { it.ID.toString() },{it.Name}) + drawsList.associateBy( { it.Name },{it.ID.toString()})
    val TypeMap = typesList.associateBy( { it.ID.toString() },{it.Name}) + typesList.associateBy( { it.Name },{it.ID.toString()})

    var visibleState by remember{
        mutableStateOf(false)
    }
    var simb by remember{
        mutableStateOf("⌄")
    }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(
                Color(MaterialTheme.colorScheme.primary.toArgb()),
                shape = RoundedCornerShape(30.dp)
            )
            .border(
                width = 2.dp,
                color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))

    ){
        Button(
            onClick = {
                visibleState = !visibleState
                if (simb == "⌄") {
                    simb = "⌃"
                } else {
                    simb = "⌄"
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(
                    width = 2.dp,
                    color = Color(MaterialTheme.colorScheme.secondary.toArgb()),
                    shape = RoundedCornerShape(30.dp)
                )
                .clip(RoundedCornerShape(30.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)

                    .clip(RoundedCornerShape(30.dp))
            )
            {
                Text(
                    text = settItem.Name,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    fontSize = 20.sp

                )
                Text(
                    text = simb,
                    color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                    fontSize = 20.sp
                )//⌃
            }
        }
        AnimatedVisibility(
            visible = visibleState,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))

            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded= !expanded
                    },

                    ) {
                    TextField(
                        readOnly = true,
                        value = TypeMap.get(settItem.TypeCount.toString()).toString(),
                        onValueChange = { },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.surface,
                        ),
                        label = {
                            Text(
                                text = stringResource(R.string.group_by),
                                color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                                fontSize = 15.sp
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        },
                        modifier = Modifier
                            .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    ) {
                        for (item_draw in typesList) {
                            DropdownMenuItem(
                                text = { Text(
                                    text = item_draw.Name,
                                    color = Color(MaterialTheme.colorScheme.surface.toArgb())) },
                                onClick = {
                                    settItem.TypeCount = TypeMap.get(item_draw.Name)!!.toInt()
                                    expanded = false
                                },
                                modifier = Modifier
                                    .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                            )
                        }
                    }
                }
                TextField(
                    value = settItem.CountVals.toString(),
                    onValueChange = {
                        settItem.CountVals = it.toInt()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.surface,
                        ),
                    label = {
                        Text(
                            text = stringResource(R.string.Count),
                            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                            fontSize = 15.sp
                        )
                    }
                )
                TextField(
                    value = settItem.Priority.toString(),
                    onValueChange = {
                        settItem.Priority = it.toInt()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.surface,
                        ),
                    label = {
                        Text(
                            text = stringResource(R.string.Priority),
                            color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                            fontSize = 15.sp
                        )
                    }
                )
                ExposedDropdownMenuBox(
                    expanded = expanded1,
                    onExpandedChange = {
                        expanded1 = !expanded1
                    },

                    ) {
                    TextField(
                        readOnly = true,
                        value = DrawMap.get(settItem.DrawingType.toString()).toString(),
                        onValueChange = { },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded1
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor = MaterialTheme.colorScheme.surface,
                        ),
                        label = {
                            Text(
                                text = stringResource(R.string.presentation),
                                color = Color(MaterialTheme.colorScheme.surface.toArgb()),
                                fontSize = 15.sp
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded1,
                        onDismissRequest = {
                            expanded1 = false
                        },
                        modifier = Modifier
                            .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                    ) {
                        for (item_draw in drawsList) {
                            Log.e("Itemss", item_draw.Name)
                            DropdownMenuItem(
                                text = { Text(
                                    text = item_draw.Name,
                                    color = Color(MaterialTheme.colorScheme.surface.toArgb())) },
                                onClick = {
                                    settItem.DrawingType = DrawMap.get(item_draw.Name)!!.toInt()
                                    expanded1 = false
                                },
                                 modifier = Modifier
                                     .background(Color(MaterialTheme.colorScheme.background.toArgb()))
                            )
                        }
                    }

                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Активно",
                        fontSize = 15.sp,
                        modifier = Modifier
                            //.padding(start = 20.dp)
                    )
                    Checkbox(
                        checked = expanded2,
                        onCheckedChange = {
                            settItem.Active = it
                            expanded2 = it              },
                        modifier = Modifier
                            .scale(2.5f)
                            .padding( bottom = 10.dp,top = 5.dp)
                    )
                }
                

            }

        }
    }
    }



