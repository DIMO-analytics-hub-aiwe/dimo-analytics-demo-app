package com.aiweapps.dinsurance.di.modules

import androidx.compose.material3.SnackbarHostState
import com.aiweapps.dinsurance.data.LoginRepository
import com.aiweapps.dinsurance.data.datastore.StateFlowDatasource
import com.aiweapps.dinsurance.data.datastore.TokensDatastore
import com.aiweapps.dinsurance.data.dto.TokenResponseDTO
import io.ktor.client.HttpClient
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

fun Module.httpClientModule() {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = com.aiweapps.dinsurance.utils.json)
            }
        }
    }
}

fun Module.repositories() {
    singleOf(::LoginRepository)
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