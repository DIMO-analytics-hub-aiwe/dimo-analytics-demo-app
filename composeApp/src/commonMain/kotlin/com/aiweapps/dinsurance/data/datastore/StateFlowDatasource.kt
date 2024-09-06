package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.ContextHolder
import com.aiweapps.dinsurance.utils.coroutines.runBlocking
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

private val SecureStoredMutableValueJson: Json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

class StateFlowDatasource<T : Any>(
    private val name: String,
    private val contextHolder: ContextHolder,
    private val serializer: KSerializer<T>,
    private val default: T,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val settings: Settings = createDatastoreSettings(
        name = name,
        contextHolder = contextHolder
    )

    private fun readStoredValue(): T = when (val readEncoded = settings.getStringOrNull(name)) {
        null -> default
        else -> SecureStoredMutableValueJson.decodeFromString(serializer, readEncoded)
    }

    private fun writeStoredValue(value: T) {
        val encoded = SecureStoredMutableValueJson.encodeToString(serializer, value)
        settings.putString(name, encoded)
    }

    private val _stateFlow: MutableStateFlow<T> =
        MutableStateFlow(runBlocking(dispatcher) { readStoredValue() })
    val stateFlow: StateFlow<T> = _stateFlow.asStateFlow()

    fun update(function: (oldValue: T) -> T): T =
        _stateFlow.updateAndGet { current ->
            function(current).also { writeStoredValue(it) }
        }
}

fun <T : Any> StateFlowDatasource<T>.updateInScope(
    scope: CoroutineScope,
    f: (oldValue: T) -> T,
): Deferred<T> = scope.async {
    this@updateInScope.update(f)
}