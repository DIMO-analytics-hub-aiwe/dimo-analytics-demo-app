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

    fun setTokens(
        tokens: TokenResponseDTO,
        invokeOnCompletion: (() -> Unit)? = null
    ) {
        updateInScope(
            value = {
                tokens
            },
            invokeOnCompletion = invokeOnCompletion
        )
    }
}