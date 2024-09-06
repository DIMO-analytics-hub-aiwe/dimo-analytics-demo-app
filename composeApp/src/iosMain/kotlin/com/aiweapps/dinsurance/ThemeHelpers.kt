package com.aiweapps.dinsurance

import com.aiweapps.dinsurance.presentation.theme.backgroundDark
import com.aiweapps.dinsurance.presentation.theme.backgroundLight
import platform.UIKit.UIColor

@Suppress("UNUSED")
val MD_THEME_LIGHT_BACKGROUND: UIColor = UIColor(
    red = backgroundLight.red.toDouble(),
    green = backgroundLight.green.toDouble(),
    blue = backgroundLight.blue.toDouble(),
    alpha = backgroundLight.alpha.toDouble(),
)

@Suppress("UNUSED")
val MD_THEME_DARK_BACKGROUND: UIColor = UIColor(
    red = backgroundDark.red.toDouble(),
    green = backgroundDark.green.toDouble(),
    blue = backgroundDark.blue.toDouble(),
    alpha = backgroundDark.alpha.toDouble(),
)