package com.example.dashboard.data.model

import android.content.res.Resources.Theme

sealed class ThemeWrapper {
    class Light() : ThemeWrapper()
    class Dark() : ThemeWrapper()
}