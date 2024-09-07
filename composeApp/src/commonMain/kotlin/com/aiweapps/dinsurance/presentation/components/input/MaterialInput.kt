package com.aiweapps.dinsurance.presentation.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_4
import com.aiweapps.dinsurance.presentation.theme.primaryGray600
import d_insurance.composeapp.generated.resources.EnterPassword
import d_insurance.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MaterialInput(
    modifier: Modifier = Modifier,
    name: String,
    text: String,
    focusManager: FocusManager = LocalFocusManager.current,
    singleLine: Boolean = true,
    onTextChange: (String) -> Unit,
    errorMessage: StringResource? = null,
    hintMessage: StringResource? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    buttonType: FormFieldButtonType = FormFieldButtonType.Next,
    password: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var showPassword by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            isError = isError,
            enabled = enabled,
            modifier = modifier.fillMaxWidth().onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
            label = { if (text.isEmpty() && !isFocused) Text(text = name) },
            shape = MaterialTheme.shapes.medium,
            singleLine = singleLine,
            visualTransformation = if (password && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType).run {
                if (password) copy(keyboardType = KeyboardType.Password) else this
            }.copy(imeAction = buttonType.imeAction),
            keyboardActions = if (buttonType == FormFieldButtonType.Default) KeyboardActions.Default else
                KeyboardActions(
                    onNext = on(buttonType == FormFieldButtonType.Next) {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    },
                    onDone = on(buttonType == FormFieldButtonType.Done) { focusManager.clearFocus() },
                ),
            trailingIcon = trailingIcon ?: if (password) {
                { PasswordIcon(showPassword, onIconClick = { showPassword = !showPassword }) }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = primaryGray600,
                focusedTextColor = primaryGray600,
                unfocusedLabelColor = primaryGray600,
                focusedLabelColor = primaryGray600,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
        if (isError) {
            errorMessage?.let {
                Text(
                    text = stringResource(errorMessage),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = Material3_Dp_4)
                )
            }
        }
        hintMessage?.let {
            Text(
                text = stringResource(hintMessage),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = Material3_Dp_4)
            )
        }
    }


}

enum class FormFieldButtonType(val imeAction: ImeAction) {
    Next(ImeAction.Next),
    Done(ImeAction.Done),
    Default(ImeAction.Default)
}

private fun <A> on(
    condition: Boolean,
    action: (A) -> Unit,
): (A) -> Unit = if (condition) action else fun(_: A) {}

@Composable
private fun PasswordIcon(
    showPassword: Boolean,
    onIconClick: () -> Unit,
) {
    IconButton(onClick = onIconClick) {
        Icon(
            imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = stringResource(Res.string.EnterPassword),
            tint = primaryGray600
        )
    }
}