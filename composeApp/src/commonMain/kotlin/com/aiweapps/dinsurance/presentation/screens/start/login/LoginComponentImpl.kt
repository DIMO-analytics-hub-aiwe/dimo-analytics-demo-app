package com.aiweapps.dinsurance.presentation.screens.start.login

import com.aiweapps.dinsurance.data.LoginRepository
import com.aiweapps.dinsurance.extensions.launchSafe
import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.aiweapps.dinsurance.utils.oauth.OAuthCallbackHandler
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.SomethingWentWrong
import d_insurance.composeapp.generated.resources.SuccessGettingTokens
import kotlinx.coroutines.launch
import org.koin.core.component.get

class LoginComponentImpl(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
) : LoginComponent, BaseComponent(componentContext) {

    private val repository: LoginRepository = get()
    private val _state = MutableValue(initialValue = LoginState())
    override val state: Value<LoginState> = _state

    init {
        scope.launchSafe(
            onAction = {
                OAuthCallbackHandler.authCodeFlow
                    .collect { code ->
                        if (code == null) {
                            snack(stringResource = Res.string.SomethingWentWrong)
                        } else {
                            println("Code: $code")
                            _state.update {
                                it.copy(isLoading = true)
                            }
                            repository.exchangeCodeForToken(code = code)
                            _state.update {
                                it.copy(isLoading = false)
                            }
                            //TODO навигация на главную
                            snack(stringResource = Res.string.SuccessGettingTokens)
                        }
                    }
            },
            onError = { error ->
                scope.launch {
                    snack(message = error.message.orEmpty())
                }
            }
        )
    }

    override fun onBackPressed() = onBack()

    override fun onLoginPressed() {

    }

    override fun onLoginViaDimoPressed() {
        println("Start login flow")
        repository.launchOAuth(contextHolder = contextHolder)
    }

}