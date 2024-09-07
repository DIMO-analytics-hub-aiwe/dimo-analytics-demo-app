package com.aiweapps.dinsurance.presentation.screens.main

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.value.Value

interface MainComponent {

    val state: Value<MainState>

    fun onSelectCar(carInfo: CarInfo)

    fun onViewAllTripsClicked()

}

@Immutable
data class MainState(
    val cars: List<CarInfo> = emptyList(),
    val selectedCarInfo: CarInfo? = null,
    val isLoading: Boolean = false
)

data class CarInfo(
    val name: String,
    val mileage: Float,
    val lastTrip: Float,
    val fuel: Float
)