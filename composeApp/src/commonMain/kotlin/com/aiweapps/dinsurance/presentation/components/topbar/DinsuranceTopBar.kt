package com.aiweapps.dinsurance.presentation.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_4
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DinsuranceTopBar(
    text: String,
    onBackClicked: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        },
        navigationIcon = {
            onBackClicked?.let {
                Icon(
                    modifier = Modifier
                        .padding(all = Material3_Dp_8)
                        .clip(shape = CircleShape)
                        .clickable(onClick = onBackClicked)
                        .padding(all = Material3_Dp_4),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}