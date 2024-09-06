package com.aiweapps.dinsurance.data.datastore

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.aiweapps.dinsurance.data.ContextHolder
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createDatastoreSettings(
    name: String,
    contextHolder: ContextHolder,
): Settings {
    val masterKey = MasterKey.Builder(contextHolder.context.applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    val sharedPreferences = EncryptedSharedPreferences.create(
        contextHolder.context.applicationContext,
        name,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    return SharedPreferencesSettings(sharedPreferences)
}