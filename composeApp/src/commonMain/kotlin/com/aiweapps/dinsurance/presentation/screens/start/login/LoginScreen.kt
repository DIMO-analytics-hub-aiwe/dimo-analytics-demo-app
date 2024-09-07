package com.aiweapps.dinsurance.presentation.screens.start.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.aiweapps.dinsurance.presentation.components.buttons.DinsurancePrimaryButton
import com.aiweapps.dinsurance.presentation.components.buttons.DinsuranceSecondaryButton
import com.aiweapps.dinsurance.presentation.components.input.MaterialInput
import com.aiweapps.dinsurance.presentation.components.snackbar.DinsuranceSnackbar
import com.aiweapps.dinsurance.presentation.icons.DimoLogo
import com.aiweapps.dinsurance.presentation.screens.start.common.WelcomeDimoComponent
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_24
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_4
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_40
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_48
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_50
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import d_insurance.composeapp.generated.resources.ButtonLogin
import d_insurance.composeapp.generated.resources.ButtonLoginViaDimo
import d_insurance.composeapp.generated.resources.EnterEmail
import d_insurance.composeapp.generated.resources.EnterPassword
import d_insurance.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun LoginScreen(
    component: LoginComponent,
) {
    var text by remember {
        mutableStateOf("")
    }
    var pwd by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = Material3_Dp_16
        ).clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = focusManager::clearFocus
        ),
        topBar = {
            Icon(
                modifier = Modifier
                    .padding(vertical = Material3_Dp_40)
                    .clip(shape = CircleShape)
                    .clickable(onClick = component::onBackPressed),
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        containerColor = Color.Transparent,
        snackbarHost = {
            DinsuranceSnackbar()
        }
    ) {
        val state by component.state.subscribeAsState()
        WelcomeDimoComponent(contentSpacing = Material3_Dp_50) {
            MaterialInput(
                name = stringResource(resource = Res.string.EnterEmail),
                text = text,
                enabled = state.isLoading.not(),
                focusManager = focusManager,
                onTextChange = {
                    text = it
                }
            )
            Spacer(modifier = Modifier.height(height = Material3_Dp_12))
            MaterialInput(
                name = stringResource(resource = Res.string.EnterPassword),
                text = pwd,
                focusManager = focusManager,
                password = true,
                enabled = state.isLoading.not(),
                onTextChange = {
                    pwd = it
                }
            )
            Spacer(modifier = Modifier.height(height = Material3_Dp_50))
            DinsurancePrimaryButton(
                text = stringResource(resource = Res.string.ButtonLogin),
                enabled = state.isLoading.not(),
                onClick = component::onLoginPressed
            )
            Spacer(modifier = Modifier.height(height = Material3_Dp_20))
            DinsuranceSecondaryButton(
                text = stringResource(resource = Res.string.ButtonLoginViaDimo),
                enabled = state.isLoading.not(),
                icon = {
                    Icon(
                        modifier = Modifier.size(
                            size = Material3_Dp_24
                        ),
                        imageVector = Icons.DimoLogo,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                    )
                },
                onClick = component::onLoginViaDimoPressed
            )
        }
    }
}