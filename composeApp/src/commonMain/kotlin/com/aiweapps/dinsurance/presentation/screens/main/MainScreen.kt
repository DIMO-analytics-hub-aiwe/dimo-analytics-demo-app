package com.aiweapps.dinsurance.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aiweapps.dinsurance.presentation.screens.start.common.DrivingScoreView
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import d_insurance.composeapp.generated.resources.DrivingScore
import d_insurance.composeapp.generated.resources.Fuel
import d_insurance.composeapp.generated.resources.ImproveDrivingStyle
import d_insurance.composeapp.generated.resources.LastTrip
import d_insurance.composeapp.generated.resources.Mileage
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.TipsForYour
import d_insurance.composeapp.generated.resources.ViewAllTrips
import d_insurance.composeapp.generated.resources.YourCars
import d_insurance.composeapp.generated.resources.fuel
import d_insurance.composeapp.generated.resources.golf
import d_insurance.composeapp.generated.resources.mileage
import d_insurance.composeapp.generated.resources.trip
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
                title = {
                    Text(stringResource(resource = Res.string.YourCars), fontSize = 24.sp)
                }
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
                        .clip(RoundedCornerShape(16.dp))
                )
                BadgesView(carInfo = info, component = component)
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
        Button(onClick = { expanded = true }, modifier = Modifier.height(40.dp)) {
            Text(info.name, color = Color.White, fontSize = 17.sp)
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
                    text = { Text(it.name, modifier = Modifier.padding(10.dp), fontSize = 17.sp) }
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
            DrivingScoreView(progress = 0.7f)
            Text(stringResource(resource = Res.string.ImproveDrivingStyle),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = { }) {
                    Text(stringResource(resource = Res.string.TipsForYour), fontSize = 16.sp, color = Color.White)
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select car", tint = Color.White)
                }
            }
        }
    }
}

@Composable
private fun BadgesView(carInfo: CarInfo, component: MainComponent) {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)) {
        Row(horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.weight(1.4f),
                title = stringResource(resource = Res.string.Mileage),
                value = "${carInfo.mileage}  mi",
                icon = painterResource(Res.drawable.mileage)
            )
            InfoBadge(modifier = Modifier.weight(1f),
                title = stringResource(resource = Res.string.LastTrip),
                value = "${carInfo.lastTrip}  mi",
                icon = painterResource(Res.drawable.trip)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.requiredWidth(140.dp),
                title = stringResource(resource = Res.string.Fuel),
                value = "${carInfo.fuel} l",
                icon = painterResource(Res.drawable.fuel)
            )

            Button(onClick = component::onViewAllTripsClicked, modifier = Modifier.height(50.dp).fillMaxWidth()) {
                Text(stringResource(resource = Res.string.ViewAllTrips), color = Color.White, fontSize = 18.sp)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select car", tint = Color.White)
            }
        }
    }
}

@Composable
private fun InfoBadge(modifier: Modifier = Modifier,
                      title: String, value: String, icon: Painter) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .background(Color.Red)
        .padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        }
        Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}