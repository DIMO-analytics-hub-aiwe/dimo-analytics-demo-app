package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.utils.coroutines.IO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseDatastore<T : Any>(
    private val datasource: StateFlowDatasource<T>,
) {

    protected val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private fun invokeOnCompletionOnMainThread(
        invokeOnCompletion: (() -> Unit)? = null,
    ) {
        scope.launch(Dispatchers.Main.immediate) {
            invokeOnCompletion?.invoke()
        }
    }

    protected fun updateInScope(
        value: (T) -> T,
        invokeOnCompletion: (() -> Unit)? = null,
    ) {
        datasource.updateInScope(
            scope = scope,
            f = value
        ).invokeOnCompletion {
            invokeOnCompletionOnMainThread(
                invokeOnCompletion = invokeOnCompletion
            )
        }
    }
}