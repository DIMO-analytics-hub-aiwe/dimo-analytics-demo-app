package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.dto.TokenResponseDTO

class TokensDatastore(
    val tokensDatasource: StateFlowDatasource<TokenResponseDTO>,
) : BaseDatastore<TokenResponseDTO>(
    datasource = tokensDatasource
) {

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