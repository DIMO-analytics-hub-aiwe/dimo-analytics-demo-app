package com.aiweapps.dinsurance.data

import com.aiweapps.dinsurance.data.datastore.TokensDatastore
import com.aiweapps.dinsurance.network.ApiService

class LoginRepository(
    private val apiService: ApiService,
    private val tokensDataStore: TokensDatastore,
) {

    fun launchOAuth(contextHolder: ContextHolder) {
        apiService.launchOAuth(contextHolder = contextHolder)
    }

    suspend fun exchangeCodeForToken(
        code: String,
    ) {
        val result = apiService.fetchTokens(
            code = code
        )
        println("LoginFlow: tokens: $result")
        tokensDataStore.storeTokens(
            tokens = result,
            authCode = code,
        )
    }
}