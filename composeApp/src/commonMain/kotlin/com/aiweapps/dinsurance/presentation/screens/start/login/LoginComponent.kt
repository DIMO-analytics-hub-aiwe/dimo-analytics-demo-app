package com.aiweapps.dinsurance.presentation.screens.start.login

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.value.Value

interface LoginComponent {

    val state: Value<LoginState>

    fun onBackPressed()

    fun onLoginPressed()

    fun onLoginViaDimoPressed()
}

@Immutable
data class LoginState(
    val isLoading: Boolean = false
)