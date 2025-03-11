package com.example.core.DI

import android.app.Activity
import android.app.Application
import com.example.core.domain.api.ThemeRepository
import dagger.Subcomponent
import javax.inject.Singleton


@Subcomponent(
    modules = [

    ],
)
interface CoreComponent {
    fun inject(application: Application)
    fun inject(activity: Activity)
    fun themeRepository(): ThemeRepository

    @Subcomponent.Factory
    interface Factory{
        fun create(): CoreComponent
    }
}