package com.aiweapps.dinsurance.presentation.screens.start.login

import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.arkivanov.decompose.ComponentContext

class LoginComponentImpl(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : LoginComponent, BaseComponent(componentContext) {

    override fun onBackPressed() = onBack()

}