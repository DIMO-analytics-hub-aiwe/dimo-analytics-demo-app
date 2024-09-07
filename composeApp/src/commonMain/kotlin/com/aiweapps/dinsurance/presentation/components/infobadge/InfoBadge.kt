package com.aiweapps.dinsurance.presentation.components.infobadge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8

@Composable
fun InfoBadge(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(Material3_Dp_8))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(Material3_Dp_16)
    ) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 22.sp)
    }
}