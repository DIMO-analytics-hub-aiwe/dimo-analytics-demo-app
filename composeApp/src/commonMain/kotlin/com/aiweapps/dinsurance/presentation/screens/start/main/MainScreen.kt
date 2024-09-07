package com.aiweapps.dinsurance.presentation.screens.start.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import d_insurance.composeapp.generated.resources.Aggressive
import d_insurance.composeapp.generated.resources.Attentive
import d_insurance.composeapp.generated.resources.ButtonLogin
import d_insurance.composeapp.generated.resources.DrivingScore
import d_insurance.composeapp.generated.resources.Fuel
import d_insurance.composeapp.generated.resources.ImproveDrivingStyle
import d_insurance.composeapp.generated.resources.LastTrip
import d_insurance.composeapp.generated.resources.Mileage
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.TipsForYour
import d_insurance.composeapp.generated.resources.ViewAllTrips
import d_insurance.composeapp.generated.resources.YourCars
import d_insurance.composeapp.generated.resources.compose_multiplatform
import d_insurance.composeapp.generated.resources.golf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    component: MainComponent,
) {
    val state by component.state.subscribeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(stringResource(resource = Res.string.YourCars)) }
            )
    }) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(Material3_Dp_16)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Material3_Dp_20)) {

            val info = state.selectedCarInfo
            if (info != null) {
                CarDropdown(info, state.cars) {
                    component.onSelectCar(it)
                }
                Image(
                    painterResource(Res.drawable.golf),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(16/9f)
                        .clip(RoundedCornerShape(8.dp))
                )
                BadgesView(info)
                DrivingScoreView()
            } else {
                Text("Loading info...")
            }

        }
    }
}

@Composable
private fun CarDropdown(info: CarInfo, cars: List<CarInfo>, onSelect: (CarInfo) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }, modifier = Modifier.height(52.dp)) {
            Text(info.name, color = Color.White, fontSize = 18.sp)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Select car")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            cars.forEach {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelect(it)
                    },
                    text = { Text(it.name, modifier = Modifier.padding(10.dp)) }
                )
            }
        }
    }
}

@Composable
private fun DrivingScoreView() {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)) {
        Text(stringResource(resource = Res.string.DrivingScore), fontWeight = FontWeight.Bold, fontSize = 20.sp)

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
                    Text(stringResource(resource = Res.string.Attentive), fontSize = 12.sp)
                    Text(stringResource(resource = Res.string.Aggressive), fontSize = 12.sp)
                }
            }
            Text(stringResource(resource = Res.string.ImproveDrivingStyle),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = { }) {
                    Text(stringResource(resource = Res.string.TipsForYour), fontSize = 16.sp, color = Color.White, textDecoration = TextDecoration.Underline)
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select car", tint = Color.White)
                }
            }
        }
    }
}

@Composable
private fun BadgesView(carInfo: CarInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)) {
        Row(horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.weight(1.5f), title = stringResource(resource = Res.string.Mileage), value = "${carInfo.mileage}  mi")
            InfoBadge(modifier = Modifier.weight(1f), title = stringResource(resource = Res.string.LastTrip), value = "${carInfo.lastTrip}  mi")
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.requiredWidth(100.dp), title = stringResource(resource = Res.string.Fuel), value = "${carInfo.fuel} l")

            Button(onClick = { }, modifier = Modifier.height(52.dp).fillMaxWidth()) {
                Text(stringResource(resource = Res.string.ViewAllTrips), color = Color.White, fontSize = 18.sp)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select car", tint = Color.White)
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