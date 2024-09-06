package com.aiweapps.dinsurance.presentation.screens.app

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.aiweapps.dinsurance.presentation.screens.login.LoginComponentImpl
import com.aiweapps.dinsurance.presentation.theme.AppThemeState
import com.aiweapps.dinsurance.presentation.theme.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import org.koin.core.component.get

class AppComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent(componentContext), AppComponent {

    private val nav = StackNavigation<Config>()
    private val _appThemeState: MutableStateFlow<AppThemeState> = MutableStateFlow(
        AppThemeState(
            contextHolder = get(),
            themeMode = ThemeMode.System
        )
    )

    override val appThemeState: Value<AppThemeState> = _appThemeState.asValue()

    private val _stack =
        childStack(
            source = nav,
            serializer = Config.serializer(),
            initialConfiguration = Config.LoginConfig, //TOOD if logged in then should be main config
            childFactory = ::child,
        )

    override val stack: Value<ChildStack<*, AppComponent.Child>> = _stack

    private fun child(config: Config, componentContext: ComponentContext): AppComponent.Child =
        when (config) {
            is Config.LoginConfig ->
                AppComponent.Child.LoginChild(
                    component = LoginComponentImpl(
                        componentContext = componentContext,
                    )
                )
        }

    override fun onBackClicked() {
        nav.pop()
    }

    override fun onBackClicked(toIndex: Int) {
        nav.popTo(index = toIndex)
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object LoginConfig : Config
    }
}
