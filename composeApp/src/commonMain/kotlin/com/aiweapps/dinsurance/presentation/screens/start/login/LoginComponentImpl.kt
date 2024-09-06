package com.aiweapps.dinsurance.presentation.screens.start.login

import com.aiweapps.dinsurance.data.LoginRepository
import com.aiweapps.dinsurance.extensions.launchSafe
import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.aiweapps.dinsurance.utils.oauth.OAuthCallbackHandler
import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get

class LoginComponentImpl(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
) : LoginComponent, BaseComponent(componentContext) {

    private val repository: LoginRepository = get()

    init {
        scope.launchSafe(
            onAction = {
                OAuthCallbackHandler.authCodeFlow
                    .collect { code ->
                        println("Code: $code")
                        code?.let {
                            println("LoginFlow: tokens: ${repository.exchangeCodeForToken(code = it)}")
                        }
                    }
            },
            onError = { error ->
                println("LoginFlow: error: ${error.message}")
            }
        )
    }

    override fun onBackPressed() = onBack()

    override fun onLoginPressed() {

    }

    override fun onLoginViaDimoPressed() {
        repository.launchOAuth(contextHolder = contextHolder)
    }

}