package com.example.core.data.model

import android.content.res.Resources.Theme

sealed class ThemeWrapper {
    class Light() : ThemeWrapper()
    class Dark() : ThemeWrapper()
}