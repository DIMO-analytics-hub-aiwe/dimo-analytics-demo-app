package com.aiweapps.dinsurance.extensions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_0
import kotlinx.coroutines.launch
import kotlin.math.abs

const val DEFAULT_CROSS_FADE_ANIMATION_SPEED = 500
const val DEFAULT_SHIMMER_ANIMATION_SPEED = 1000

@Composable
fun Modifier.onSizeChanged(
    content: (Pair<Dp, Dp>) -> Unit
): Modifier = composed {
    val density = LocalDensity.current
    this.onGloballyPositioned { layoutCoordinates ->
        val width = with(density) { layoutCoordinates.size.width.toDp() }
        val height = with(density) { layoutCoordinates.size.height.toDp() }
        if (width > Material3_Dp_0 && height > Material3_Dp_0) {
            content(Pair(width, height))
        }
    }
}

@Composable
fun Modifier.swipeableWithAnchors(
    anchorDpValues: List<Dp>,
    content: @Composable (Dp) -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val offsetX = remember { Animatable(0f) }
    val anchors = remember { anchorDpValues.map { with(density) { it.toPx() } } }

    this.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragEnd = {
                val targetAnchor = anchors.minByOrNull { abs(it - offsetX.value) } ?: 0f
                scope.launch {
                    offsetX.animateTo(
                        targetValue = targetAnchor,
                        animationSpec = spring(stiffness = Spring.StiffnessMedium)
                    )
                }
            }
        ) { change, dragAmount ->
            change.consume()
            scope.launch {
                offsetX.snapTo((offsetX.value + dragAmount).coerceIn(anchors.minOrNull() ?: 0f, anchors.maxOrNull() ?: 0f))
            }
        }
    }.apply {
        content(with(density) { offsetX.value.toDp() })
    }
}

fun Modifier.thenIf(condition: Boolean, other: Modifier): Modifier =
    if (condition) this.then(other) else this

fun Modifier.shimmerLoading(): Modifier = composed {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = DEFAULT_SHIMMER_ANIMATION_SPEED,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim.value - 300f, translateAnim.value - 300f),
        end = Offset(translateAnim.value, translateAnim.value)
    )

    this.background(brush)
}
