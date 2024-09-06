package com.aiweapps.dinsurance.presentation.screens.start.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun MainScreen(
    component: MainComponent,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Main screen", modifier = Modifier.align(Alignment.Center))
    }
}