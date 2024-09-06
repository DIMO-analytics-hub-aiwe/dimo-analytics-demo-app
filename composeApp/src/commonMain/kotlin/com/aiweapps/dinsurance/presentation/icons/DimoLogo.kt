package com.aiweapps.dinsurance.presentation.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

public val Icons.DimoLogo: ImageVector
    get() {
        if (_icdimologo != null) {
            return _icdimologo!!
        }
        _icdimologo = Builder(
            name = "Icdimologo",
            defaultWidth = 128.0.dp,
            defaultHeight = 128.0.dp,
            viewportWidth = 128.0f,
            viewportHeight = 128.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF010101)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(24.6f, 30.1f)
                curveToRelative(-6.8f, 0.0f, -12.6f, 6.1f, -12.6f, 13.1f)
                verticalLineToRelative(66.6f)
                horizontalLineTo(0.0f)
                verticalLineTo(43.3f)
                curveToRelative(0.0f, -13.9f, 11.0f, -25.1f, 24.6f, -25.1f)
                reflectiveCurveToRelative(23.4f, 11.2f, 23.4f, 25.1f)
                verticalLineToRelative(47.6f)
                curveToRelative(0.0f, 3.6f, 3.0f, 7.0f, 7.7f, 7.0f)
                reflectiveCurveToRelative(4.6f, -1.9f, 5.7f, -4.1f)
                lineToRelative(30.9f, -64.9f)
                curveToRelative(3.1f, -6.5f, 9.7f, -10.6f, 16.9f, -10.7f)
                curveToRelative(10.4f, 0.0f, 18.8f, 8.6f, 18.8f, 19.1f)
                verticalLineToRelative(72.5f)
                horizontalLineToRelative(-12.0f)
                verticalLineTo(37.3f)
                curveToRelative(0.0f, -3.7f, -3.2f, -7.2f, -6.8f, -7.2f)
                reflectiveCurveToRelative(-4.8f, 1.9f, -5.9f, 4.2f)
                lineToRelative(-30.9f, 65.0f)
                curveToRelative(-3.1f, 6.4f, -9.6f, 10.5f, -16.7f, 10.5f)
                curveToRelative(-10.3f, 0.0f, -19.7f, -8.4f, -19.7f, -18.9f)
                verticalLineToRelative(-47.6f)
                curveToRelative(0.0f, -7.0f, -4.5f, -13.1f, -11.4f, -13.1f)
            }
        }
            .build()
        return _icdimologo!!
    }

private var _icdimologo: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.DimoLogo, contentDescription = null)
    }
}
