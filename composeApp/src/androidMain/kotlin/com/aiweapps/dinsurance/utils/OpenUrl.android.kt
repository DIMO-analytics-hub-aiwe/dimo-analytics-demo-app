package com.aiweapps.dinsurance.utils

import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.aiweapps.dinsurance.data.ContextHolder

actual fun launchBrowser(contextHolder: ContextHolder, url: String) =
    CustomTabsIntent
        .Builder()
        .setDefaultColorSchemeParams(
            CustomTabColorSchemeParams.Builder()
                .setToolbarColor(
                    ContextCompat.getColor(
                        contextHolder.context,
                        android.R.color.black
                    )
                )
                .build()
        )
        .build()
        .also { it ->
            it.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
        .launchUrl(contextHolder.context, Uri.parse(url))