package com.aiweapps.dinsurance.extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_0
import kotlinx.coroutines.CoroutineScope

@Composable
fun OnceLaunchedEffect(block: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit, block)
}

fun PaddingValues.addPadding(
    top: Dp = Material3_Dp_0,
    bottom: Dp = Material3_Dp_0,
    start: Dp = Material3_Dp_0,
    end: Dp = Material3_Dp_0,
): PaddingValues =
    PaddingValues(
        top = this.calculateTopPadding() + top,
        bottom = this.calculateBottomPadding() + bottom,
        start = this.calculateStartPadding(LayoutDirection.Ltr) + start,
        end = this.calculateEndPadding(LayoutDirection.Ltr) + end
    )

fun Dp.isGreaterThan(other: Dp) = this > other

fun Dp.isGreaterThanZero(): Boolean = isGreaterThan(other = Material3_Dp_0)