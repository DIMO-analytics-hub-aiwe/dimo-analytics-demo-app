package com.aiweapps.dinsurance.presentation.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_50
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8

@Composable
fun DinsurancePrimaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth().height(Material3_Dp_50),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors().copy(
            containerColor = MaterialTheme.colorScheme.outline,
            contentColor = Color.Black
        ),
        onClick = onClick,
    ) {
        Text(
            text = text
        )
    }
}

@Composable
fun DinsuranceSecondaryButton(
    text: String,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth().height(Material3_Dp_50),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors().copy(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = Color.Black
        ),
        onClick = onClick,
    ) {
        icon?.invoke()
        Spacer(modifier = Modifier.width(width = Material3_Dp_8))
        Text(
            text = text
        )
    }
}