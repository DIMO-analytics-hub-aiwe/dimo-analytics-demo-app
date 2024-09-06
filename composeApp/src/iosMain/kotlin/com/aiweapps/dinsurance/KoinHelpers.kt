package com.aiweapps.dinsurance

import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.presentation.initDinsuranceApp
import com.aiweapps.dinsurance.utils.VibrationManager
import org.koin.dsl.module

@Suppress("UNUSED")
fun iosDinsuranceApp() {
    initDinsuranceApp {
        modules(module {
            single { ContextHolder() }
            single { VibrationManager() }
        })
    }
}
