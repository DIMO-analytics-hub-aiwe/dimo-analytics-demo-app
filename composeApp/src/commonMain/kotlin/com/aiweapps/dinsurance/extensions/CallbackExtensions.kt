package com.aiweapps.dinsurance.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DEBOUNCE_TIME_MILLIS = 1000L

fun debounceClick(
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
    delayMillis: Long = DEBOUNCE_TIME_MILLIS,
    onClick: () -> Unit
) = debounce(delayMillis = delayMillis, scope = scope) {
    onClick()
}

private fun debounce(
    delayMillis: Long = DEBOUNCE_TIME_MILLIS,
    scope: CoroutineScope,
    action: () -> Unit
): () -> Unit {
    var debounceJob: Job? = null
    return {
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action()
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}