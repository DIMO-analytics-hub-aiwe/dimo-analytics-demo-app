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

public val Icons.History: ImageVector
    get() {
        if (_history != null) {
            return _history!!
        }
        _history = Builder(
            name = "History",
            defaultWidth = 28.0.dp,
            defaultHeight = 28.0.dp,
            viewportWidth = 28.0f,
            viewportHeight = 28.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.0f, 24.5f)
                curveTo(11.317f, 24.5f, 8.978f, 23.61f, 6.985f, 21.831f)
                curveTo(4.992f, 20.052f, 3.85f, 17.831f, 3.558f, 15.167f)
                horizontalLineTo(5.95f)
                curveTo(6.222f, 17.189f, 7.122f, 18.861f, 8.648f, 20.183f)
                curveTo(10.174f, 21.506f, 11.958f, 22.167f, 14.0f, 22.167f)
                curveTo(16.275f, 22.167f, 18.205f, 21.374f, 19.79f, 19.79f)
                curveTo(21.374f, 18.205f, 22.167f, 16.275f, 22.167f, 14.0f)
                curveTo(22.167f, 11.725f, 21.374f, 9.795f, 19.79f, 8.21f)
                curveTo(18.205f, 6.626f, 16.275f, 5.833f, 14.0f, 5.833f)
                curveTo(12.658f, 5.833f, 11.404f, 6.144f, 10.238f, 6.767f)
                curveTo(9.071f, 7.389f, 8.089f, 8.244f, 7.292f, 9.333f)
                horizontalLineTo(10.5f)
                verticalLineTo(11.667f)
                horizontalLineTo(3.5f)
                verticalLineTo(4.667f)
                horizontalLineTo(5.833f)
                verticalLineTo(7.408f)
                curveTo(6.825f, 6.164f, 8.035f, 5.201f, 9.465f, 4.521f)
                curveTo(10.894f, 3.84f, 12.406f, 3.5f, 14.0f, 3.5f)
                curveTo(15.458f, 3.5f, 16.824f, 3.777f, 18.098f, 4.331f)
                curveTo(19.372f, 4.885f, 20.48f, 5.634f, 21.423f, 6.577f)
                curveTo(22.366f, 7.52f, 23.115f, 8.628f, 23.669f, 9.902f)
                curveTo(24.223f, 11.176f, 24.5f, 12.542f, 24.5f, 14.0f)
                curveTo(24.5f, 15.458f, 24.223f, 16.824f, 23.669f, 18.098f)
                curveTo(23.115f, 19.372f, 22.366f, 20.48f, 21.423f, 21.423f)
                curveTo(20.48f, 22.366f, 19.372f, 23.115f, 18.098f, 23.669f)
                curveTo(16.824f, 24.223f, 15.458f, 24.5f, 14.0f, 24.5f)
                close()
                moveTo(17.267f, 18.9f)
                lineTo(12.833f, 14.467f)
                verticalLineTo(8.167f)
                horizontalLineTo(15.167f)
                verticalLineTo(13.533f)
                lineTo(18.9f, 17.267f)
                lineTo(17.267f, 18.9f)
                close()
            }
        }
            .build()
        return _history!!
    }

private var _history: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.History, contentDescription = null)
    }
}
