package com.example.dash.DI

import android.app.Application
import dagger.Subcomponent

@Subcomponent(
    modules = [

    ]
)
interface DashboardComponent {
    fun inject(application: Application)

    @Subcomponent.Factory
    interface Factory{
        fun create() : DashboardComponent
    }
}