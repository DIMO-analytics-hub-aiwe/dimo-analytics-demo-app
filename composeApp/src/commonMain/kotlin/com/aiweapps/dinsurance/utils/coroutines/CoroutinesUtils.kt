package com.aiweapps.dinsurance.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

expect fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T

expect val Dispatchers.IO: CoroutineDispatcher