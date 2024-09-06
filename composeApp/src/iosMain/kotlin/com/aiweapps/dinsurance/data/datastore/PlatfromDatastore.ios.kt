package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.ContextHolder
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings

@OptIn(ExperimentalSettingsImplementation::class)
actual fun createDatastoreSettings(
    name: String,
    contextHolder: ContextHolder,
): Settings  = KeychainSettings(name)