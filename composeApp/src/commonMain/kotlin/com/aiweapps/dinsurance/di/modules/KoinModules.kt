package com.aiweapps.dinsurance.di.modules

import androidx.compose.material3.SnackbarHostState
import com.aiweapps.dinsurance.data.LoginRepository
import com.aiweapps.dinsurance.data.TripHistoryRepository
import com.aiweapps.dinsurance.data.datastore.StateFlowDatasource
import com.aiweapps.dinsurance.data.datastore.TokensDatastore
import com.aiweapps.dinsurance.data.dto.TokenResponseDTO
import com.aiweapps.dinsurance.network.ApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named

private val TOKENS_DATASOURCE_QUALIFIER: StringQualifier = named("tokensDatastore")


fun Module.snackbarHostStateModule() {
    singleOf(::SnackbarHostState)
}

fun Module.repositories() {
    singleOf(::LoginRepository)
    singleOf(::TripHistoryRepository)
}

fun Module.datastores() {
    single(qualifier = TOKENS_DATASOURCE_QUALIFIER) {
        StateFlowDatasource(
            name = "user-preferences",
            contextHolder = get(),
            serializer = TokenResponseDTO.serializer(),
            default = TokenResponseDTO(),
        )
    }
    single { TokensDatastore(tokensDatasource = get(qualifier = TOKENS_DATASOURCE_QUALIFIER)) }
}

fun Module.networkModule() {
    single {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(json = com.aiweapps.dinsurance.utils.json)
            }
            install(plugin = Auth) {
                bearer {
                    loadTokens {
                        val datastore = get<TokensDatastore>()
                        val currentTokes = datastore.tokensDatasource.stateFlow.value
                        val accessToken = currentTokes.accessToken ?: return@loadTokens null
                        BearerTokens(
                            accessToken = accessToken,
                            refreshToken = currentTokes.refreshToken,
                        )
                    }
                    refreshTokens {
                        val datastore = get<TokensDatastore>()
                        val service = get<ApiService>()
                        val code = datastore.tokensDatasource.stateFlow.value.authCode
                            ?: return@refreshTokens null
                        val newTokens = service.fetchTokens(code = code)
                        datastore.storeTokens(tokens = newTokens, authCode = code)
                        val accessToken = datastore.tokensDatasource.stateFlow.value.accessToken
                            ?: return@refreshTokens null
                        BearerTokens(
                            accessToken = accessToken,
                            refreshToken = newTokens.refreshToken
                        )
                    }
                }
            }
        }
    }
    singleOf(::ApiService)
}