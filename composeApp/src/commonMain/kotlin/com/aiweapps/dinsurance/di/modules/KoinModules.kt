package com.aiweapps.dinsurance.di.modules

import androidx.compose.material3.SnackbarHostState
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf

fun Module.snackbarHostStateModule() {
    singleOf(::SnackbarHostState)
}