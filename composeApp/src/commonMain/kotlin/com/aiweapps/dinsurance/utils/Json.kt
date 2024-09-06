package com.aiweapps.dinsurance.utils

import kotlinx.serialization.json.Json

internal val json =
    Json {
        allowStructuredMapKeys = true
    }
