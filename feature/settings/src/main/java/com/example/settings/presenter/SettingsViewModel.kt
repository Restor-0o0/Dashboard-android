package com.example.settings.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.UseCase.ThemeUseCase
import com.example.core.domain.UseCase.TokenUseCase
import com.example.core.domain.model.DataWrapper
import com.example.settings.common.MODULE_TAG
import com.example.settings.domain.model.DrawingTypes
import com.example.settings.domain.model.SettingsItem
import com.example.settings.domain.model.TypesCount
import com.example.settings.domain.usecase.GetSettingsUseCase
import com.example.settings.domain.usecase.SetSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


private val TAG = "SettingsViewModel"

class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase,
    private val themeUseCase: ThemeUseCase,
    private val tokenExistUseCase: TokenUseCase,
): ViewModel() {
    val SettingsList = MutableStateFlow<List<SettingsItem>>(
        emptyList()
    )
    var TypesCountList = MutableStateFlow<List<TypesCount>>(
        emptyList()
    )

    var DrawingTypesList =  MutableStateFlow<List<DrawingTypes>>(
        emptyList()
    )

    val DrawMap: StateFlow<Map<String, String>> = DrawingTypesList.map{
        list ->
            list.associate { it.ID.toString() to it.Name  } +
            list.associate { it.Name to it.ID.toString() }

    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val TypeMap: StateFlow<Map<String,String>> = TypesCountList.map{
        list ->
            list.associate { it.ID.toString() to it.Name } +
            list.associate { it.Name to it.ID.toString() }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val saveSuccess = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)
    var httpErrorRes = MutableStateFlow<Int?>(null)
    val isDarkTheme = themeUseCase.getTheme().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        false
    )


    fun clearToken(){
        Log.d("$MODULE_TAG/$TAG","clearToken")
        tokenExistUseCase.clearToken()
    }
    fun toggleTheme(){
        Log.d("$MODULE_TAG/$TAG","toggleTheme")
        viewModelScope.launch {
            themeUseCase.saveTheme(!isDarkTheme.value)
        }
    }

    fun setSettings(){
        viewModelScope.launch {
            Log.d("$MODULE_TAG/$TAG","setSettings")
            try {
                isLoading.value = false
                val response = setSettingsUseCase.setSettings(SettingsList.value)
                when(response){
                    is DataWrapper.Error -> {
                        httpErrorRes.value = response.error.messageRes
                    }
                    is DataWrapper.Success -> {
                        saveSuccess.value = true
                    }
                }

            }
            catch (e : Exception)
            {

            }
            finally {
                isLoading.value = false
            }
        }
    }


    fun getSettings(){
        viewModelScope.launch {
            Log.d("$MODULE_TAG/$TAG","getSettings")
            try {
                isLoading.value = true

                val response = getSettingsUseCase.loadSettings()
                when(response){
                    is DataWrapper.Error -> {
                        Log.d("$MODULE_TAG/$TAG","getSettings error")
                        httpErrorRes.value = response.error.messageRes!!
                    }
                    is DataWrapper.Success -> {
                        Log.d("$MODULE_TAG/$TAG","getSettings success")
                        SettingsList.value = response.data.settings_data
                        DrawingTypesList.value = response.data.drawing_types
                        TypesCountList.value = response.data.types_count
                    }
                }
            }
            catch (e : Exception)
            {
                Log.e("$MODULE_TAG/$TAG",e.message.toString())
            }
            finally {
                isLoading.value = false
            }
        }

    }
}