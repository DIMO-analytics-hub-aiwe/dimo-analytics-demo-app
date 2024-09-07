package com.aiweapps.dinsurance.presentation.screens.triphistory

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.arkivanov.decompose.value.Value

interface TripHistoryComponent {

    val state: Value<TripHistoryState>

    fun onBackClicked()
    fun onTripClicked(item: TripHistoryItem.Item? = null)

}

@Immutable
data class TripHistoryState(
    val tripHistory: List<TripHistoryItem> = emptyList(),
    val details: TripHistoryDetailsState? = null,
)

@Immutable
data class TripHistoryDetailsState(
    val trip: TripHistoryItem.Item
)

@Stable
sealed class TripHistoryItem(
    open val id: String,
) {
    @Immutable
    data class Item(
        override val id: String,
        val date: String,
        val subtitle: String,
        val timestamp: String,
        val progress: Float,
        val isoDate: String,
        val details: Details,
    ) : TripHistoryItem(id = id) {

        @Immutable
        data class Details(
            val speed: String,
            val fuel: String,
            val distance: String,
            val travelTime: String,
        )
    }

    @Immutable
    data class Row(
        override val id: String,
        val title: String,
    ) : TripHistoryItem(id = id)
}

