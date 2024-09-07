package com.aiweapps.dinsurance.presentation.screens.start.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.aiweapps.dinsurance.presentation.components.buttons.DinsurancePrimaryButton
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    component: MainComponent,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Your cars")
                }
            )
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(Material3_Dp_16),
            verticalArrangement = Arrangement.spacedBy(Material3_Dp_20)) {

            DinsurancePrimaryButton("Text") {

            }

//        Box(modifier = Modifier.fillMaxSize()
//            .aspectRatio(16/9f)
//            .background(Color.Black)) {
//
//        }

            BadgesView()
            DrivingScoreView()
        }
    }
}

@Composable
private fun DrivingScoreView() {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)) {
        Text("Driving score", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Column(modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
            .padding(horizontal = Material3_Dp_16, vertical = Material3_Dp_12)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                val brush = Brush.horizontalGradient(listOf(Color.Green, Color.Red))
                Box(modifier = Modifier.fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                    .background(brush))

                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text("Attentive", fontSize = 12.sp)
                    Text("Aggressive", fontSize = 12.sp)
                }
            }
            Text("Your driving style needs improvement. Learn safe driving tips and get a discount")

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = {}) {
                    Text("Tips for you")
                }
            }
        }
    }
}

@Composable
private fun BadgesView() {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)) {
        Row(horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.weight(1.5f), title = "Milleage", value = "70 000 mi")
            InfoBadge(modifier = Modifier.weight(1f), title = "Last trip", value = "120 mi")
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.requiredWidth(100.dp), title = "Fuel", value = "25 l")
            DinsurancePrimaryButton("View all trips") {

            }
        }
    }
}

@Composable
private fun InfoBadge(modifier: Modifier = Modifier,
                      title: String, value: String) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(Color.Red)
        .padding(16.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 22.sp)
    }
}