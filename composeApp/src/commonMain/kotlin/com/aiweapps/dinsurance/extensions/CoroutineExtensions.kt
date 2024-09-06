package com.aiweapps.dinsurance.extensions

import com.aiweapps.dinsurance.utils.coroutines.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Extensions, which obliges to handle the error, error handling occurs through CoroutineExceptionHandler,
 *  which allows you to catch an error in child coroutines, if any.
 * @param onAction - safe action callback
 * @param onError - error callback
 * @param dispatcher - dispatcher in which the coroutine will be executed. Default is IO.
 * @param errorDispatcher - dispatcher for CoroutineExceptionHandler, in which the error will be processed. Default is Main.
 */
inline fun CoroutineScope.launchSafe(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    errorDispatcher: CoroutineDispatcher = Dispatchers.Main,
    coroutineName: String? = null,
    crossinline onError: suspend (Throwable) -> Unit,
    crossinline onAction: suspend () -> Unit,
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launch(context = errorDispatcher) {
            onError.invoke(throwable)
        }
    }
    val block: suspend CoroutineScope.() -> Unit = {
        onAction()
    }
    var coroutineContext = exceptionHandler + dispatcher
    coroutineName?.let { name ->
        coroutineContext += CoroutineName(name = name)
    }
    return this.launch(context = coroutineContext, block = block)
}