package com.aiweapps.dinsurance.network

import com.aiweapps.dinsurance.data.datastore.TokensDatastore
import com.aiweapps.dinsurance.utils.json
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun provideDinsuranceHttpClient(
    datastore: TokensDatastore,
    service: ApiService,
): HttpClient = HttpClient {
    install(plugin = ContentNegotiation) {
        json(json = json)
    }
    install(plugin = Auth) {
        bearer {
            loadTokens {
                val currentTokes = datastore.tokensDatasource.stateFlow.value
                val accessToken = currentTokes.accessToken ?: return@loadTokens null
                BearerTokens(accessToken = accessToken, refreshToken = currentTokes.refreshToken)
            }
            refreshTokens {
                val code = datastore.tokensDatasource.stateFlow.value.authCode ?: return@refreshTokens null
                val newTokens = service.fetchTokens(code = code)
                datastore.storeTokens(tokens = newTokens, authCode = code)
                val accessToken = datastore.tokensDatasource.stateFlow.value.accessToken ?: return@refreshTokens null
                BearerTokens(accessToken = accessToken, refreshToken = newTokens.refreshToken)
            }
        }
    }
}