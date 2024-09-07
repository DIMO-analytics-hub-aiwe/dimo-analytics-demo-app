package com.aiweapps.dinsurance.presentation.screens.start.main

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainComponentImpl(
    componentContext: ComponentContext,
) : MainComponent, BaseComponent(componentContext) {

    private val _state = MutableValue(initialValue = MainState())
    override val state: Value<MainState> = _state

    init {
        scope.launchSafe(
            onAction = {
                _state.update { it.copy(isLoading = true) }
                delay(300)
                _state.update {
                    val cars = listOf(
                        CarInfo("Volkswagen Golf", 54600f, 33f, 5f),
                        CarInfo("Lada Vesta", 10000f, 12f, 20f)
                    )
                    it.copy(cars = cars, selectedCarInfo = cars.firstOrNull(), isLoading = false)
                }
            },
            onError = { error ->
                _state.update { it.copy(isLoading = false) }
                scope.launch {
                    snack(message = error.message.orEmpty())
                }
            }
        )
    }

    override fun onSelectCar(carInfo: CarInfo) {
        scope.launchSafe(
            onAction = {
                _state.update { it.copy(isLoading = true) }
                delay(300)
                _state.update { it.copy(selectedCarInfo = carInfo, isLoading = false) }
            },
            onError = { error ->
                _state.update { it.copy(isLoading = false) }
                scope.launch {
                    snack(message = error.message.orEmpty())
                }
            }
        )
    }

}