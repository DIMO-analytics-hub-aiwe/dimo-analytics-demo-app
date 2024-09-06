package com.aiweapps.dinsurance.presentation.screens.start

import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.arkivanov.decompose.ComponentContext

class StartComponentImpl(
    val onLogin: () -> Unit,
    componentContext: ComponentContext,
) : StartComponent, BaseComponent(componentContext) {

    override fun onLoginClicked() = onLogin()

}