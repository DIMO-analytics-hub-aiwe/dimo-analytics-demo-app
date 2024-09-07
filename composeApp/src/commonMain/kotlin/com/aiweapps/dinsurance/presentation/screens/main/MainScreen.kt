package com.aiweapps.dinsurance.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import coil3.compose.AsyncImage
import com.aiweapps.dinsurance.presentation.components.buttons.DinsurancePrimaryButton
import com.aiweapps.dinsurance.presentation.components.views.DrivingScoreView
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_10
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_140
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_32
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_38
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import com.aiweapps.dinsurance.presentation.theme.Stroke_Dp_1
import com.aiweapps.dinsurance.presentation.theme.primaryBlack
import com.aiweapps.dinsurance.presentation.theme.primaryGray600
import com.aiweapps.dinsurance.presentation.theme.primaryMint
import com.aiweapps.dinsurance.presentation.theme.primaryPaperBadge
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
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = primaryBlack,
                    titleContentColor = primaryPaperBadge,
                ),
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

            val selected = state.selectedVehicle
            if (selected != null) {
                CarDropdown(selected, state.vehicles) {
                    component.onSelectVehicle(it)
                }
                if (selected.imageUrl != null) {
                    AsyncImage(
                        model = selected.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                            .aspectRatio(16/9f)
                            .clip(RoundedCornerShape(Material3_Dp_16))
                    )
                }
            } else if (state.isLoading) {
                LoadingView()
                return@Column
            }

            val details = state.vehicleDetails
            if (details != null) {
                BadgesView(vehicleDetails = details, component = component)
                DrivingScoreView()
            } else {
                LoadingView()
            }

        }
    }
}

@Composable
private fun CarDropdown(selected: VehicleInfo, vehicles: List<VehicleInfo>, onSelect: (VehicleInfo) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(Stroke_Dp_1))
    Box {
        TextButton(onClick = { expanded = true }, modifier = Modifier.height(Material3_Dp_38)
            .border(Stroke_Dp_1, primaryPaperBadge, RoundedCornerShape(Material3_Dp_32))) {
            Text(selected.definition.displayName, color = primaryPaperBadge, fontSize = 17.sp)
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Select car", tint = primaryMint)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            vehicles.forEach {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelect(it)
                    },
                    text = { Text(it.definition.displayName, modifier = Modifier.padding(Material3_Dp_10), fontSize = 17.sp) }
                )
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Loading...", color = primaryPaperBadge, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
private fun DrivingScoreView() {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)) {
        Text(stringResource(resource = Res.string.DrivingScore), fontWeight = FontWeight.Bold, fontSize = 20.sp, color = primaryPaperBadge)

        Column(modifier = Modifier
            .border(Stroke_Dp_1, primaryGray600, RoundedCornerShape(Material3_Dp_8))
            .clip(RoundedCornerShape(Material3_Dp_8))
            .background(Color.Transparent)
            .padding(horizontal = Material3_Dp_16, vertical = Material3_Dp_12)) {
            DrivingScoreView(progress = 0.7f)
            Text(stringResource(resource = Res.string.ImproveDrivingStyle),
                color = primaryGray600,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = { }) {
                    Text(stringResource(resource = Res.string.TipsForYour), fontSize = 16.sp, color = primaryMint)
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select car", tint = primaryMint)
                }
            }
        }
    }
}

@Composable
private fun BadgesView(vehicleDetails: VehicleDetails, component: MainComponent) {
    Column(verticalArrangement = Arrangement.spacedBy(Material3_Dp_8)) {
        Row(horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.weight(1.4f),
                title = stringResource(resource = Res.string.Mileage),
                value = "${vehicleDetails.mileage}  mi",
                icon = painterResource(Res.drawable.mileage)
            )
            InfoBadge(modifier = Modifier.weight(1f),
                title = stringResource(resource = Res.string.LastTrip),
                value = "${vehicleDetails.lastTrip}  mi",
                icon = painterResource(Res.drawable.trip)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Material3_Dp_12),
            modifier = Modifier.fillMaxWidth()) {
            InfoBadge(modifier = Modifier.requiredWidth(Material3_Dp_140),
                title = stringResource(resource = Res.string.Fuel),
                value = "${vehicleDetails.fuel} l",
                icon = painterResource(Res.drawable.fuel)
            )

            DinsurancePrimaryButton(
                text = stringResource(resource = Res.string.ViewAllTrips),
                rightIcon = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select car", tint = primaryBlack)
                }) {
                component.onViewAllTripsClicked()
            }
        }
    }
}

@Composable
private fun InfoBadge(modifier: Modifier = Modifier,
                      title: String, value: String, icon: Painter) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .background(primaryGray600.copy(alpha = 0.2f))
        .padding(Material3_Dp_16), verticalArrangement = Arrangement.spacedBy(Material3_Dp_8)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = primaryGray600)
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = primaryGray600)
        }
        Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = primaryPaperBadge)
    }
}