package com.example.dashboard.domain.UseCase

import com.example.dashboard.common.SaveManager
import com.example.dashboard.data.model.ResponseWrapper
import com.example.dashboard.data.model.SettingsResponse
import com.example.dashboard.domain.api.SettingsReposetory
import com.example.dashboard.domain.model.DrawingTypes
import com.example.dashboard.domain.model.SettingsData
import com.example.dashboard.domain.model.TypesCount
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val reposetory: SettingsReposetory,
) {

    private lateinit var settings_data: Array<DrawingTypes>
    private lateinit var drawing_types: Array<TypesCount>
    private lateinit var types_count: Array<SettingsData>

    suspend fun loadSettings(){
        //val settings: ResponseWrapper<SettingsResponse> = reposetory.getSettingsData()
    }

}