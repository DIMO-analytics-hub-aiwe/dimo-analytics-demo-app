package com.aiweapps.dinsurance.presentation.screens.start.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_2
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_4
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import com.aiweapps.dinsurance.presentation.theme.Stroke_Dp_1
import d_insurance.composeapp.generated.resources.Aggressive
import d_insurance.composeapp.generated.resources.Attentive
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.TravelProgressAlert
import org.jetbrains.compose.resources.stringResource

@Composable
fun DrivingScoreView(progress: Float, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(end = Material3_Dp_8),
            text = stringResource(resource = Res.string.TravelProgressAlert),
            style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.background,
                fontSize = 10.sp
            )
        )
        Spacer(modifier = Modifier.height(height = Material3_Dp_2))
        DrivingScoreViewProgressBar(
            modifier = Modifier.fillMaxWidth(),
            progress = progress
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = Material3_Dp_8)
        ) {
            Text(
                modifier = Modifier.weight(1F).fillMaxWidth(),
                text = stringResource(resource = Res.string.Attentive),
                style = MaterialTheme.typography.bodySmall.copy(
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.background
                )
            )
            Text(
                modifier = Modifier.weight(1F).fillMaxWidth(),
                text = stringResource(resource = Res.string.Aggressive),
                style = MaterialTheme.typography.bodySmall.copy(
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

@Composable
fun DrivingScoreViewProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0.7F,
) {
    Box(
        modifier = modifier
            .height(height = Material3_Dp_12)
            .clip(shape = RoundedCornerShape(size = Material3_Dp_8))
            .border(
                width = Stroke_Dp_1,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    size = Material3_Dp_8
                )
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Green,
                        Color.Red
                    )
                )
            )
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val markerPosition = size.width * progress
            drawLine(
                color = Color.Blue,
                start = Offset(x = markerPosition, y = 0F),
                end = Offset(x = markerPosition, y = size.height),
                strokeWidth = Material3_Dp_4.toPx()
            )
        }
    }
}