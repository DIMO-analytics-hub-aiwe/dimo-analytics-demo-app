package com.aiweapps.dinsurance.presentation.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_50
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import com.aiweapps.dinsurance.presentation.theme.primaryBlack
import com.aiweapps.dinsurance.presentation.theme.primaryMint
import com.aiweapps.dinsurance.presentation.theme.primaryWhite

@Composable
fun DinsurancePrimaryButton(
    text: String,
    enabled: Boolean = true,
    leftIcon: (@Composable () -> Unit)? = null,
    rightIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth().height(Material3_Dp_50),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors().copy(
            containerColor = primaryMint,
            contentColor = primaryBlack
        ),
        onClick = onClick,
    ) {
        Spacer(modifier = Modifier.width(width = Material3_Dp_8))
        Text(text = text, fontWeight = FontWeight.Medium, fontSize = 17.sp)
        Spacer(modifier = Modifier.width(width = Material3_Dp_8))
        rightIcon?.invoke()
    }
}

@Composable
fun DinsuranceOutlinedButton(
    text: String,
    enabled: Boolean = true,
    leftIcon: (@Composable () -> Unit)? = null,
    rightIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth().height(Material3_Dp_50),
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors().copy(
            containerColor = Color.Transparent,
            contentColor = primaryBlack
        ),
        onClick = onClick,
    ) {
        leftIcon?.invoke()
        Spacer(modifier = Modifier.width(width = Material3_Dp_8))
        Text(text = text, fontWeight = FontWeight.Medium, fontSize = 17.sp, color = primaryWhite)
        Spacer(modifier = Modifier.width(width = Material3_Dp_8))
        rightIcon?.invoke()
    }
}