package com.aiweapps.dinsurance.presentation.components.snackbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_24
import org.koin.compose.koinInject

@Composable
fun DinsuranceSnackbar(
    modifier: Modifier = Modifier,
    paddingBottom: Dp = Material3_Dp_24,
) {
    val snackbarState: SnackbarHostState = koinInject()
    SnackbarHost(
        hostState = snackbarState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(bottom = paddingBottom),
                content = {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                action = {
                    data.visuals.actionLabel?.let { actionLabel ->
                        TextButton(
                            onClick = {
                                snackbarState.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.inversePrimary
                                )
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.BottomCenter)
    )
}