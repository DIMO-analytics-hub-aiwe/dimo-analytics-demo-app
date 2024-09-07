package com.aiweapps.dinsurance.presentation.screens.main

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface MainComponent {

    val state: Value<MainState>

    fun onSelectVehicle(vehicle: VehicleInfo)

    fun onViewAllTripsClicked()

}

@Immutable
data class MainState(
    val vehicles: List<VehicleInfo> = emptyList(),
    val selectedVehicle: VehicleInfo? = null,
    val vehicleDetails: VehicleDetails? = null,
    val isLoading: Boolean = false
)

@Serializable
data class VehiclesResponse(val data: VehiclesDataResponse)
@Serializable
data class VehiclesDataResponse(val vehicles: VehiclesData)
@Serializable
data class VehiclesData(
    val totalCount: Int,
    val nodes: List<VehicleInfo>
)
@Serializable
data class VehicleInfo(
    val tokenId: Int,
    val owner: String,
    val imageURI: String?,
    val definition: VehicleInfoDefinition
) {
    val imageUrl: String?
        get() = imageURI?.replace("ipfs://", "https://ipfs.io/ipfs/")
}

@Serializable
data class VehicleInfoDefinition(
    val id: String,
    val make: String,
    val model: String,
    val year: Int
) {

    val displayName: String
        get() = listOfNotNull(make, model).joinToString(" ")
}

@Serializable
data class VehicleDetails(
    val fuel: Float,
    val mileage: Float,
    val lastTrip: Float
)