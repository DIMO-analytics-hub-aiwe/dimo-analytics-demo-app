package com.aiweapps.dinsurance.data.datastore

import com.aiweapps.dinsurance.data.ContextHolder
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual fun createDatastoreSettings(
    name: String,
    contextHolder: ContextHolder,
): Settings = PreferencesSettings(Preferences.userRoot())