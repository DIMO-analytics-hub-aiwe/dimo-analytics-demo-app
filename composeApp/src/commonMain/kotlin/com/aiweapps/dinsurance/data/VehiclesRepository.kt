package com.aiweapps.dinsurance.data

import com.aiweapps.dinsurance.network.ApiService
import com.aiweapps.dinsurance.presentation.screens.main.VehicleDetails
import com.aiweapps.dinsurance.presentation.screens.main.VehicleInfo

class VehiclesRepository(
    private val apiService: ApiService
) {

    suspend fun getMyVehicles(): List<VehicleInfo> {
        val response = apiService.getVehicles()
        return response.data.vehicles.nodes
    }

    suspend fun getVehicleDetails(vehicleTokenId: Int): VehicleDetails {
//        return apiService.getVehicleDetails(vehicleTokenId)
        return VehicleDetails(
            100f,
            2000f,
            45f
        )
    }

}