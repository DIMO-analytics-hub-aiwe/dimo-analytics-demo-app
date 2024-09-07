package com.aiweapps.dinsurance.presentation.screens.main

import com.aiweapps.dinsurance.data.VehiclesRepository
import com.aiweapps.dinsurance.extensions.launchSafe
import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.get

class MainComponentImpl(
    private val onViewAllTrips: () -> Unit,
    componentContext: ComponentContext,
) : MainComponent, BaseComponent(componentContext) {

    private val repository: VehiclesRepository = get()

    private val _state = MutableValue(initialValue = MainState())
    override val state: Value<MainState> = _state

    init {
        scope.launchSafe(
            onAction = {
                _state.update { it.copy(isLoading = true) }
                val vehicles = repository.getMyVehicles()
                _state.update {
                    it.copy(vehicles = vehicles, selectedVehicle = vehicles.firstOrNull(), isLoading = false)
                }
                vehicles.firstOrNull()?.let { vehicle ->
                    onSelectVehicle(vehicle)
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

    override fun onSelectVehicle(vehicle: VehicleInfo) {
        scope.launchSafe(
            onAction = {
                _state.update { it.copy(selectedVehicle = vehicle, isLoading = true) }
                val details = repository.getVehicleDetails(vehicle.tokenId)
                _state.update { it.copy(vehicleDetails = details, isLoading = false) }
            },
            onError = { error ->
                _state.update { it.copy(isLoading = false) }
                scope.launch {
                    snack(message = error.message.orEmpty())
                }
            }
        )
    }

    override fun onViewAllTripsClicked() = onViewAllTrips()

}