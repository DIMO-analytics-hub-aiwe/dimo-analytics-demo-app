package com.aiweapps.dinsurance.utils

expect class VibrationManager {
    fun vibrate(durationInMillis: Long = DEFAULT_VIBRATION_DURATION)
}

private const val DEFAULT_VIBRATION_DURATION = 200L