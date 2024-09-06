package com.aiweapps.dinsurance.presentation.screens.start.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.aiweapps.dinsurance.presentation.icons.DimoLogo
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_48
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import d_insurance.composeapp.generated.resources.LoginSubtitle
import d_insurance.composeapp.generated.resources.LoginTitle
import d_insurance.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeDimoComponent(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .padding(horizontal = Material3_Dp_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(
                size = Material3_Dp_48
            ),
            imageVector = Icons.DimoLogo,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(height = Material3_Dp_8))
        Text(
            text = stringResource(resource = Res.string.LoginTitle),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = stringResource(resource = Res.string.LoginSubtitle),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        content()
    }
}