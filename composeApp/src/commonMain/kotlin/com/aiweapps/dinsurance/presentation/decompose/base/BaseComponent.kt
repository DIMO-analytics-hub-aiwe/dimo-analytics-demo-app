package com.aiweapps.dinsurance.presentation.decompose.base

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.presentation.decompose.asValueUtil
import com.aiweapps.dinsurance.presentation.decompose.coroutineScope
import com.aiweapps.dinsurance.utils.VibrationManager
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.combine as coroutinesFlowCombine
import kotlinx.coroutines.flow.map as coroutinesFlowMap

abstract class BaseComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, KoinComponent {

    protected val scope: CoroutineScope
        get() = coroutineScope(Dispatchers.Main.immediate)
    protected val vibrationManager: VibrationManager = get()
    protected val contextHolder: ContextHolder = get()

    private val snackbarHostState: SnackbarHostState = get<SnackbarHostState>()

    suspend fun snack(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (withDismissAction) SnackbarDuration.Indefinite else SnackbarDuration.Short,
    ) {
        snackbarHostState.apply {
            currentSnackbarData?.dismiss()
            showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    fun snack(
        stringResource: StringResource,
        vararg formatArgs: Any = emptyArray(),
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (withDismissAction) SnackbarDuration.Indefinite else SnackbarDuration.Short,
    ) {
        scope.launch {
            snack(
                message = getString(resource = stringResource, formatArgs),
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
        }
    }

    protected fun <T1 : Any, T2 : Any, R : Any> combine(
        value1: Value<T1>,
        value2: Value<T2>,
        combineFunction: (T1, T2) -> R
    ): Value<R> {
        val combinedValue = MutableValue(combineFunction(value1.value, value2.value))

        val observer1: (T1) -> Unit = { newValue1 ->
            combinedValue.value = combineFunction(newValue1, value2.value)
        }
        value1.subscribe(observer1)

        val observer2: (T2) -> Unit = { newValue2 ->
            combinedValue.value = combineFunction(value1.value, newValue2)
        }
        value2.subscribe(observer2)
        return combinedValue
    }

    protected fun <T, M> StateFlow<T>.map(
        coroutineScope: CoroutineScope = scope,
        mapper: (value: T) -> M,
    ): StateFlow<M> =
        coroutinesFlowMap(mapper)
            .stateIn(
                coroutineScope,
                SharingStarted.Eagerly,
                mapper(value),
            )

    protected fun <T1, T2, R> combine(
        flow1: StateFlow<T1>,
        flow2: StateFlow<T2>,
        coroutineScope: CoroutineScope = scope,
        transform: (T1, T2) -> R,
    ): StateFlow<R> =
        coroutinesFlowCombine(flow1, flow2, transform)
            .stateIn(
                coroutineScope,
                SharingStarted.Eagerly,
                transform(flow1.value, flow2.value)
            )

    protected fun <T1, T2, T3, R> combine(
        flow1: StateFlow<T1>,
        flow2: StateFlow<T2>,
        flow3: StateFlow<T3>,
        coroutineScope: CoroutineScope = scope,
        transform: (T1, T2, T3) -> R,
    ): StateFlow<R> =
        coroutinesFlowCombine(flow1, flow2, flow3, transform)
            .stateIn(
                coroutineScope,
                SharingStarted.Eagerly,
                transform(flow1.value, flow2.value, flow3.value)
            )

    protected fun <T1, T2, T3, T4, R> combine(
        flow1: StateFlow<T1>,
        flow2: StateFlow<T2>,
        flow3: StateFlow<T3>,
        flow4: StateFlow<T4>,
        coroutineScope: CoroutineScope = scope,
        transform: (T1, T2, T3, T4) -> R,
    ): StateFlow<R> =
        coroutinesFlowCombine(flow1, flow2, flow3, flow4, transform)
            .stateIn(
                coroutineScope,
                SharingStarted.Eagerly,
                transform(flow1.value, flow2.value, flow3.value, flow4.value)
            )

    protected fun <T : Any> StateFlow<T>.asValue(
        lifecycle: Lifecycle = this@BaseComponent.lifecycle,
        context: CoroutineContext = Dispatchers.Main.immediate,
    ): Value<T> = asValueUtil(lifecycle, context)
}