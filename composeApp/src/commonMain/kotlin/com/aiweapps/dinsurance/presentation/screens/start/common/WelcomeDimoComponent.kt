package com.aiweapps.dinsurance.presentation.screens.start.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.Dp
import com.aiweapps.dinsurance.presentation.icons.DimoLogo
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_32
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_4
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_48
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_80
import d_insurance.composeapp.generated.resources.LoginSubtitle
import d_insurance.composeapp.generated.resources.LoginTitle
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.app_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeDimoComponent(
    contentSpacing: Dp = Material3_Dp_80,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painterResource(Res.drawable.app_logo), contentDescription = null)
        Spacer(modifier = Modifier.height(height = Material3_Dp_32))
        Text(
            text = stringResource(resource = Res.string.LoginTitle),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(height = Material3_Dp_4))
        Text(
            text = stringResource(resource = Res.string.LoginSubtitle),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(height = contentSpacing))
        content()
    }
}