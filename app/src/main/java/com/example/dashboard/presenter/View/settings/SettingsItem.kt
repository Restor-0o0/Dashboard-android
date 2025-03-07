package com.example.dashboard.presenter.View.settings

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.R
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsItem(
    settItem: SettingsData,
    drawsList: MutableList<DrawingTypes>,
    typesList: MutableList<TypesCount>
) {
    var expanded by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

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