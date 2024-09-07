package com.aiweapps.dinsurance.presentation.screens.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiweapps.dinsurance.presentation.components.buttons.DinsurancePrimaryButton
import com.aiweapps.dinsurance.presentation.components.buttons.DinsuranceOutlinedButton
import com.aiweapps.dinsurance.presentation.screens.start.common.WelcomeDimoComponent
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_24
import d_insurance.composeapp.generated.resources.ButtonCreateNewAccount
import d_insurance.composeapp.generated.resources.ButtonLogin
import d_insurance.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun StartScreen(
    component: StartComponent,
) {
    Box(modifier = Modifier.padding(horizontal = Material3_Dp_16)) {
        WelcomeDimoComponent {
            Spacer(modifier = Modifier.height(height = Material3_Dp_24))
            DinsurancePrimaryButton(
                text = stringResource(resource = Res.string.ButtonLogin),
                onClick = component::onLoginClicked
            )
            Spacer(modifier = Modifier.height(height = Material3_Dp_20))
            DinsuranceOutlinedButton(
                text = stringResource(resource = Res.string.ButtonCreateNewAccount),
                onClick = {

                }
            )
        }
    }
}
