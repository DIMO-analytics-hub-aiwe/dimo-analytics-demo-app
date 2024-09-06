package com.aiweapps.dinsurance.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.dm_sans_black
import d_insurance.composeapp.generated.resources.dm_sans_bold
import d_insurance.composeapp.generated.resources.dm_sans_extra_bold
import d_insurance.composeapp.generated.resources.dm_sans_extra_light
import d_insurance.composeapp.generated.resources.dm_sans_light
import d_insurance.composeapp.generated.resources.dm_sans_medium
import d_insurance.composeapp.generated.resources.dm_sans_regular
import d_insurance.composeapp.generated.resources.dm_sans_semibold
import d_insurance.composeapp.generated.resources.dm_sans_thin
import org.jetbrains.compose.resources.Font

@Composable
private fun dmSans() = FontFamily(
    Font(resource = Res.font.dm_sans_black, weight = FontWeight.Black),
    Font(resource = Res.font.dm_sans_extra_bold, weight = FontWeight.ExtraBold),
    Font(resource = Res.font.dm_sans_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.dm_sans_semibold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.dm_sans_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.dm_sans_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.dm_sans_light, weight = FontWeight.Light),
    Font(resource = Res.font.dm_sans_extra_light, weight = FontWeight.ExtraLight),
    Font(resource = Res.font.dm_sans_thin, weight = FontWeight.Thin),
)

// Default Material 3 typography values
private val baseline = Typography()

@Composable
fun DinsuranceTypography() = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = dmSans()),
    displayMedium = baseline.displayMedium.copy(fontFamily = dmSans()),
    displaySmall = baseline.displaySmall.copy(fontFamily = dmSans()),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = dmSans()),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = dmSans()),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = dmSans()),
    titleLarge = baseline.titleLarge.copy(fontFamily = dmSans()),
    titleMedium = baseline.titleMedium.copy(fontFamily = dmSans()),
    titleSmall = baseline.titleSmall.copy(fontFamily = dmSans()),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = dmSans()),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = dmSans()),
    bodySmall = baseline.bodySmall.copy(fontFamily = dmSans()),
    labelLarge = baseline.labelLarge.copy(fontFamily = dmSans()),
    labelMedium = baseline.labelMedium.copy(fontFamily = dmSans()),
    labelSmall = baseline.labelSmall.copy(fontFamily = dmSans()),
)

