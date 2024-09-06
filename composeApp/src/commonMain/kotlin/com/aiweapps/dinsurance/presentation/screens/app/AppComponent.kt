package com.aiweapps.dinsurance.presentation.screens.app

import com.aiweapps.dinsurance.presentation.screens.start.login.LoginComponent
import com.aiweapps.dinsurance.presentation.screens.start.StartComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.aiweapps.dinsurance.presentation.theme.AppThemeState

interface AppComponent : BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>
    val appThemeState: Value<AppThemeState>

    fun onBackClicked()
    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class StartChild(val component: StartComponent) : Child()
        class LoginChild(val component: LoginComponent) : Child()
    }
}