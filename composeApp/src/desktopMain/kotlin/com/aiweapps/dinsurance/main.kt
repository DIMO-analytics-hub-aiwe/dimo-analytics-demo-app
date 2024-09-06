package com.aiweapps.dinsurance

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.presentation.initDinsuranceApp
import com.aiweapps.dinsurance.presentation.screens.app.AppComponentImpl
import com.aiweapps.dinsurance.presentation.screens.app.DinsuranceApp
import com.aiweapps.dinsurance.utils.VibrationManager
import com.aiweapps.dinsurance.utils.runOnUiThread
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.koin.compose.KoinContext
import org.koin.dsl.module

fun main(): Unit = desktopDinsuranceApp()

fun desktopDinsuranceApp() {
    initDinsuranceApp {
        modules(module {
            single { ContextHolder() }
            single { VibrationManager() }
        })
    }
    val lifecycle = LifecycleRegistry()
    val component = runOnUiThread {
        AppComponentImpl(DefaultComponentContext(lifecycle))
    }
    application {
        KoinContext {
            val windowState = rememberWindowState(
                height = DEFAULT_DESKTOP_SIZE.height,
                width = DEFAULT_DESKTOP_SIZE.width,
            )

            LifecycleController(lifecycle, windowState)

            Window(
                title = "D-Insurance",
                onCloseRequest = ::exitApplication,
                state = windowState,
                resizable = false,
                icon = null,
            ) {
                DinsuranceApp(component = component)
            }
        }
    }
}

private val DEFAULT_DESKTOP_SIZE: DpSize = DpSize(560.dp, 800.dp)