package com.aiweapps.dinsurance.presentation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.utils.VibrationManager
import com.arkivanov.decompose.defaultComponentContext
import com.aiweapps.dinsurance.presentation.screens.app.AppComponentImpl
import com.aiweapps.dinsurance.presentation.screens.app.DinsuranceApp
import org.koin.compose.KoinContext
import org.koin.dsl.module

fun ComponentActivity.androidDinsuranceApp() {
    val activity = this@androidDinsuranceApp
    initDinsuranceApp {
        modules(
            module {
                single { ContextHolder(context = activity) }
                single { VibrationManager(context = activity.applicationContext) }
            }
        )
    }
    setContent {
        KoinContext {
            DinsuranceApp(
                component = AppComponentImpl(
                    componentContext = defaultComponentContext()
                )
            )
        }
    }
}