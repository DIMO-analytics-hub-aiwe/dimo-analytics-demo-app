package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.dto.TokenResponseDTO

class TokensDatastore(
    val tokensDatasource: StateFlowDatasource<TokenResponseDTO>,
) : BaseDatastore<TokenResponseDTO>(
    datasource = tokensDatasource
) {

    fun isLoggedIn(): Boolean {
        println("QQ access token ${tokensDatasource.stateFlow.value.accessToken}")
        return tokensDatasource.stateFlow.value.accessToken != null
    }

    fun storeTokens(
        tokens: TokenResponseDTO,
        authCode: String? = null,
        invokeOnCompletion: (() -> Unit)? = null
    ) {
        updateInScope(
            value = { value ->
                value.copy(
                    accessToken = tokens.accessToken,
                    refreshToken = tokens.refreshToken,
                    expiresIn = tokens.expiresIn,
                    authCode = authCode ?: value.authCode
                )
            },
            invokeOnCompletion = invokeOnCompletion
        )
    }
}