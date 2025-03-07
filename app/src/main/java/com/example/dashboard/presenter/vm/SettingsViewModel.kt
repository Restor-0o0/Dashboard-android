package com.example.dashboard.presenter.vm

import androidx.lifecycle.ViewModel
import com.example.dashboard.domain.UseCase.GetSettingsUseCase
import com.example.dashboard.domain.UseCase.SetSettingsUseCase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase
): ViewModel() {

    val DrawMap = drawsList.associateBy( { it.ID.toString() },{it.Name}) + drawsList.associateBy( { it.Name },{it.ID.toString()})
    val TypeMap = typesList.associateBy( { it.ID.toString() },{it.Name}) + typesList.associateBy( { it.Name },{it.ID.toString()})

    var SettingsList = remember{ mutableListOf<SettingsData>() }
    var TypesCountList = remember{ mutableListOf<TypesCount>() }
    var DrawingTypesList = remember{ mutableListOf<DrawingTypes>() }




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
}