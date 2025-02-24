package com.example.dashboard.DI

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class SaveManagerModule {

    @Provides
    @Reusable
    fun provideSaveManager(context: Context) : SaveManager{
        return SaveManager(context)
    }

}