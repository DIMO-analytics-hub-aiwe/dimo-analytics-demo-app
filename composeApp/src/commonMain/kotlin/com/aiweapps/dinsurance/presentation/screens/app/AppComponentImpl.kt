package com.aiweapps.dinsurance.presentation.screens.app

import com.aiweapps.dinsurance.data.datastore.TokensDatastore
import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.aiweapps.dinsurance.presentation.screens.main.MainComponentImpl
import com.aiweapps.dinsurance.presentation.screens.start.StartComponentImpl
import com.aiweapps.dinsurance.presentation.screens.start.login.LoginComponentImpl
import com.aiweapps.dinsurance.presentation.screens.triphistory.TripHistoryComponentImpl
import com.aiweapps.dinsurance.presentation.theme.AppThemeState
import com.aiweapps.dinsurance.presentation.theme.ThemeMode
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import org.koin.core.component.get

class AppComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent(componentContext), AppComponent {

    private val tokensDatastore: TokensDatastore = get()

    private val nav = StackNavigation<Config>()
    private val _appThemeState: MutableStateFlow<AppThemeState> = MutableStateFlow(
        AppThemeState(
            contextHolder = get(),
            themeMode = ThemeMode.Light //TODO should be system later by default
        )
    )

    override val appThemeState: Value<AppThemeState> = _appThemeState.asValue()

    private val _stack =
        childStack(
            source = nav,
            serializer = Config.serializer(),
            initialConfiguration = getInitialConfig(),
            childFactory = ::child,
        )

    override val stack: Value<ChildStack<*, AppComponent.Child>> = _stack

    private fun getInitialConfig(): Config  {
        return if (!tokensDatastore.isLoggedIn()) {
            Config.MainConfig
        } else {
            Config.StartConfig
        }
    }

    private fun child(config: Config, componentContext: ComponentContext): AppComponent.Child =
        when (config) {
            is Config.StartConfig ->
                AppComponent.Child.StartChild(
                    component = StartComponentImpl(
                        componentContext = componentContext,
                        onLogin = {
                            nav.push(configuration = Config.LoginConfig)
                        }
                    )
                )
            is Config.LoginConfig -> AppComponent.Child.LoginChild(
                component = LoginComponentImpl(
                    componentContext = componentContext,
                    onBack = {
                        onBackClicked()
                    },
                    onSuccessLogin =  {
                        nav.replaceAll(Config.MainConfig)
                    }
                )
            )
            is Config.MainConfig -> AppComponent.Child.MainChild(
                component = MainComponentImpl(
                    componentContext = componentContext,
                    onViewAllTrips = {
                        nav.push(configuration = Config.TripHistory)
                    }
                )
            )
            is Config.TripHistory -> AppComponent.Child.TripHistoryChild(
                component = TripHistoryComponentImpl(
                    componentContext = componentContext,
                    onBack = ::onBackClicked
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
        data object StartConfig : Config

        @Serializable
        data object LoginConfig : Config

        @Serializable
        data object MainConfig : Config

        @Serializable
        data object TripHistory : Config
    }
}
