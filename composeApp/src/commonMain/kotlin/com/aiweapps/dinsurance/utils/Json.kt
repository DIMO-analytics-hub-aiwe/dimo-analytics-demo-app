package com.aiweapps.dinsurance.utils

import kotlinx.serialization.json.Json

internal val json =
    Json {
        allowStructuredMapKeys = true
        isLenient = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
