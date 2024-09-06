package com.aiweapps.dinsurance

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.aiweapps.dinsurance.presentation.screens.app.AppComponent
import com.aiweapps.dinsurance.presentation.screens.app.DinsuranceApp
import org.koin.compose.KoinContext
import platform.UIKit.UIViewController

@Suppress("UNUSED") //Using in RootView.swift
@OptIn(ExperimentalDecomposeApi::class)
fun appViewController(
    appComponent: AppComponent,
    backDispatcher: BackDispatcher,
): UIViewController =
    ComposeUIViewController {
        KoinContext {
            PredictiveBackGestureOverlay(
                backDispatcher = backDispatcher,
                backIcon = { progress, _ ->
                    PredictiveBackGestureIcon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        progress = progress,
                    )
                },
                modifier = Modifier.fillMaxSize(),
            ) {
                DinsuranceApp(component = appComponent, modifier = Modifier.fillMaxSize())
            }
        }
    }