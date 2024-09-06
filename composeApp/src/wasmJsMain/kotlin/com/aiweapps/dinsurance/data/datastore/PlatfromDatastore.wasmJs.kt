package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.ContextHolder
import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings

actual fun createDatastoreSettings(
    name: String,
    contextHolder: ContextHolder,
): Settings = StorageSettings()