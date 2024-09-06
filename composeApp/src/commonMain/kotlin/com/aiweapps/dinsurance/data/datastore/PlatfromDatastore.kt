package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.ContextHolder
import com.russhwolf.settings.Settings

expect fun createDatastoreSettings(name: String, contextHolder: ContextHolder): Settings
