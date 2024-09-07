package com.aiweapps.dinsurance.presentation.screens.triphistory

import com.aiweapps.dinsurance.data.TripHistoryRepository
import com.aiweapps.dinsurance.presentation.decompose.base.BaseComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import org.koin.core.component.get

class TripHistoryComponentImpl(
    private val onBack: () -> Unit,
    componentContext: ComponentContext,
) : TripHistoryComponent, BaseComponent(componentContext) {

    private val repository: TripHistoryRepository = get()

    private val _state = MutableValue(TripHistoryState())
    override val state: Value<TripHistoryState> = _state


    init {
        _state.update { state ->
            state.copy(
                tripHistory = repository.getMockTripHistory()
            )
        }
    }

    override fun onTripClicked(item: TripHistoryItem.Item?) {
        _state.update { state ->
            state.copy(
                details = if (item == null) null else TripHistoryDetailsState(
                    trip = item
                )
            )
        }
    }

    override fun onBackClicked() = onBack()

}