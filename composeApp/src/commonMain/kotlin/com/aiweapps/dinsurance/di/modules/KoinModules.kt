package com.aiweapps.dinsurance.di.modules

import androidx.compose.material3.SnackbarHostState
import com.aiweapps.dinsurance.data.LoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf

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