package com.aiweapps.dinsurance.presentation.components.systembars

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import com.aiweapps.dinsurance.data.ContextHolder

internal val DefaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

internal val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

actual fun edgeToEdgeModeController(
    contextHolder: ContextHolder,
    isDarkMode: Boolean,
) {
    val activity = (contextHolder.context as? ComponentActivity)
    activity?.enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.auto(
            lightScrim = Color.TRANSPARENT,
            darkScrim = Color.TRANSPARENT,
            detectDarkMode = {
                isDarkMode
            }
        ),
        navigationBarStyle = SystemBarStyle.auto(
            lightScrim = DefaultLightScrim,
            darkScrim = DefaultDarkScrim,
            detectDarkMode = {
                isDarkMode
            }
        ),
    )
}