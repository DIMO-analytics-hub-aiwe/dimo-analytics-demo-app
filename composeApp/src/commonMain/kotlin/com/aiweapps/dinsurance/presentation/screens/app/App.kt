package com.aiweapps.dinsurance.presentation.screens.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiweapps.dinsurance.presentation.screens.start.login.LoginScreen
import com.aiweapps.dinsurance.presentation.screens.start.StartScreen
import com.aiweapps.dinsurance.presentation.screens.start.main.MainScreen
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.aiweapps.dinsurance.presentation.theme.DinsuranceTheme

@Composable
fun DinsuranceApp(component: AppComponent, modifier: Modifier = Modifier) {
    DinsuranceTheme(
        component = component
    ) {
        Children(
            component = component,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun Children(component: AppComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(fade() + scale()),
            onBack = component::onBackClicked,
        ),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (val child = it.instance) {
                is AppComponent.Child.StartChild -> StartScreen(
                    component = child.component
                )
                is AppComponent.Child.LoginChild -> LoginScreen(
                    component = child.component
                )
                is AppComponent.Child.MainChild -> MainScreen(
                    component = child.component
                )
            }
        }
    }
}