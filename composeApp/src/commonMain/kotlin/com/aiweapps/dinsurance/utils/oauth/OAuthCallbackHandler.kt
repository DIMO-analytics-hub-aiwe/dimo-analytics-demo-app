package com.aiweapps.dinsurance.utils.oauth

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object OAuthCallbackHandler {
    private val _authCodeFlow = MutableSharedFlow<String?>(replay = 1)
    val authCodeFlow = _authCodeFlow.asSharedFlow()

    suspend fun handleAuthCode(code: String?) {
        _authCodeFlow.emit(code)
    }
}