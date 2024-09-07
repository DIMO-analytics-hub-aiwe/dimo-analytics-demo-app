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

public val Icons.GasStation: ImageVector
    get() {
        if (_gasStation != null) {
            return _gasStation!!
        }
        _gasStation = Builder(
            name = "GasStation",
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
                moveTo(4.667f, 24.5f)
                verticalLineTo(5.833f)
                curveTo(4.667f, 5.192f, 4.895f, 4.642f, 5.352f, 4.185f)
                curveTo(5.809f, 3.728f, 6.358f, 3.5f, 7.0f, 3.5f)
                horizontalLineTo(14.0f)
                curveTo(14.642f, 3.5f, 15.191f, 3.728f, 15.648f, 4.185f)
                curveTo(16.105f, 4.642f, 16.333f, 5.192f, 16.333f, 5.833f)
                verticalLineTo(14.0f)
                horizontalLineTo(17.5f)
                curveTo(18.142f, 14.0f, 18.691f, 14.229f, 19.148f, 14.685f)
                curveTo(19.605f, 15.142f, 19.833f, 15.692f, 19.833f, 16.333f)
                verticalLineTo(21.583f)
                curveTo(19.833f, 21.914f, 19.945f, 22.191f, 20.169f, 22.415f)
                curveTo(20.392f, 22.638f, 20.67f, 22.75f, 21.0f, 22.75f)
                curveTo(21.331f, 22.75f, 21.608f, 22.638f, 21.831f, 22.415f)
                curveTo(22.055f, 22.191f, 22.167f, 21.914f, 22.167f, 21.583f)
                verticalLineTo(13.183f)
                curveTo(21.992f, 13.281f, 21.807f, 13.344f, 21.613f, 13.373f)
                curveTo(21.418f, 13.402f, 21.214f, 13.417f, 21.0f, 13.417f)
                curveTo(20.183f, 13.417f, 19.493f, 13.135f, 18.929f, 12.571f)
                curveTo(18.365f, 12.007f, 18.083f, 11.317f, 18.083f, 10.5f)
                curveTo(18.083f, 9.878f, 18.254f, 9.319f, 18.594f, 8.823f)
                curveTo(18.934f, 8.327f, 19.386f, 7.972f, 19.95f, 7.758f)
                lineTo(17.5f, 5.308f)
                lineTo(18.725f, 4.083f)
                lineTo(23.042f, 8.283f)
                curveTo(23.333f, 8.575f, 23.552f, 8.915f, 23.698f, 9.304f)
                curveTo(23.844f, 9.693f, 23.917f, 10.092f, 23.917f, 10.5f)
                verticalLineTo(21.583f)
                curveTo(23.917f, 22.4f, 23.635f, 23.09f, 23.071f, 23.654f)
                curveTo(22.507f, 24.218f, 21.817f, 24.5f, 21.0f, 24.5f)
                curveTo(20.183f, 24.5f, 19.493f, 24.218f, 18.929f, 23.654f)
                curveTo(18.365f, 23.09f, 18.083f, 22.4f, 18.083f, 21.583f)
                verticalLineTo(15.75f)
                horizontalLineTo(16.333f)
                verticalLineTo(24.5f)
                horizontalLineTo(4.667f)
                close()
                moveTo(7.0f, 11.667f)
                horizontalLineTo(14.0f)
                verticalLineTo(5.833f)
                horizontalLineTo(7.0f)
                verticalLineTo(11.667f)
                close()
                moveTo(21.0f, 11.667f)
                curveTo(21.331f, 11.667f, 21.608f, 11.555f, 21.831f, 11.331f)
                curveTo(22.055f, 11.108f, 22.167f, 10.831f, 22.167f, 10.5f)
                curveTo(22.167f, 10.169f, 22.055f, 9.892f, 21.831f, 9.669f)
                curveTo(21.608f, 9.445f, 21.331f, 9.333f, 21.0f, 9.333f)
                curveTo(20.67f, 9.333f, 20.392f, 9.445f, 20.169f, 9.669f)
                curveTo(19.945f, 9.892f, 19.833f, 10.169f, 19.833f, 10.5f)
                curveTo(19.833f, 10.831f, 19.945f, 11.108f, 20.169f, 11.331f)
                curveTo(20.392f, 11.555f, 20.67f, 11.667f, 21.0f, 11.667f)
                close()
                moveTo(7.0f, 22.167f)
                horizontalLineTo(14.0f)
                verticalLineTo(14.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(22.167f)
                close()
            }
        }
            .build()
        return _gasStation!!
    }

private var _gasStation: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.GasStation, contentDescription = null)
    }
}
