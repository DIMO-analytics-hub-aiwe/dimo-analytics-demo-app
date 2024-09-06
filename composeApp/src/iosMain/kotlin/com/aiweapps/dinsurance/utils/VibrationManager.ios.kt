package com.aiweapps.dinsurance.utils

import platform.AudioToolbox.AudioServicesPlaySystemSound
import platform.AudioToolbox.kSystemSoundID_Vibrate

actual class VibrationManager {
    actual fun vibrate(durationInMillis: Long) =
        AudioServicesPlaySystemSound(kSystemSoundID_Vibrate)
}