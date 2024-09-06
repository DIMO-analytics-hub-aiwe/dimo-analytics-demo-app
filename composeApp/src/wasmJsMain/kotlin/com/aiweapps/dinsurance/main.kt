package com.aiweapps.dinsurance

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.presentation.initDinsuranceApp
import com.aiweapps.dinsurance.utils.VibrationManager
import kotlinx.browser.document
import org.koin.dsl.module

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        initDinsuranceApp {
            modules(module {
                single { ContextHolder() }
                single { VibrationManager() }
            })
        }
    }
}