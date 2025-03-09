package com.example.settings.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.UseCase.ThemeUseCase
import com.example.settings.domain.model.DrawingTypes
import com.example.settings.domain.model.SettingsData
import com.example.settings.domain.model.TypesCount
import com.example.settings.domain.usecase.GetSettingsUseCase
import com.example.settings.domain.usecase.SetSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase,
    private val themeUseCase: ThemeUseCase
): ViewModel() {


    init {

    }

   // val DrawMap = drawsList.associateBy( { it.ID.toString() },{it.Name}) + drawsList.associateBy( { it.Name },{it.ID.toString()})
    //val TypeMap = typesList.associateBy( { it.ID.toString() },{it.Name}) + typesList.associateBy( { it.Name },{it.ID.toString()})

    private var SettingsList = MutableStateFlow(
        SettingsData(
        settings_data = TODO(),
        drawing_types = TODO(),
        types_count = TODO()
    )
    )
    var TypesCountList = MutableStateFlow(
        TypesCount(
        ID = TODO(),
        Name = TODO()
    )
    )
    var DrawingTypesList =  MutableStateFlow(
        DrawingTypes(
        ID = TODO(),
        Name = TODO(),
        Comment = TODO()
    )
    )

    var isLoading = MutableStateFlow(false)

    var httpErrorRes = MutableStateFlow(0)
    val isDarkTheme = themeUseCase.getTheme().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        false
    )



    fun dataSend(){/*
        viewModelScope.launch {
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
    */
    }


    fun dataSett(){/*
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
    */
    }
}