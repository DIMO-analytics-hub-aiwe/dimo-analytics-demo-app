package com.aiweapps.dinsurance.presentation

import com.aiweapps.dinsurance.di.modules.datastores
import com.aiweapps.dinsurance.di.modules.networkModule
import com.aiweapps.dinsurance.di.modules.repositories
import com.aiweapps.dinsurance.di.modules.snackbarHostStateModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

internal fun initDinsuranceApp(appDeclaration: KoinAppDeclaration = {}) {
    stopKoin()
    startKoin {
        appDeclaration()
        modules(module {
            snackbarHostStateModule()
            datastores()
            networkModule()
            repositories()
        })
    }
}