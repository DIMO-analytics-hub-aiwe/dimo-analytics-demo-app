package com.aiweapps.dinsurance.presentation.screens.triphistory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aiweapps.dinsurance.presentation.components.topbar.DinsuranceTopBar
import com.aiweapps.dinsurance.presentation.components.views.DrivingScoreView
import com.aiweapps.dinsurance.presentation.components.views.DrivingScoreViewProgressBar
import com.aiweapps.dinsurance.presentation.icons.Distance
import com.aiweapps.dinsurance.presentation.icons.GasStation
import com.aiweapps.dinsurance.presentation.icons.History
import com.aiweapps.dinsurance.presentation.icons.Speed
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_12
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_16
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_20
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_24
import com.aiweapps.dinsurance.presentation.theme.Material3_Dp_8
import com.aiweapps.dinsurance.presentation.theme.Stroke_Dp_1
import com.aiweapps.dinsurance.presentation.theme.bottomSheetBackground
import com.aiweapps.dinsurance.presentation.theme.primaryGray600
import com.aiweapps.dinsurance.presentation.theme.primaryPaperBadge
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import d_insurance.composeapp.generated.resources.AverageSpeed
import d_insurance.composeapp.generated.resources.Distance
import d_insurance.composeapp.generated.resources.DrivingScore
import d_insurance.composeapp.generated.resources.FuelConsumption
import d_insurance.composeapp.generated.resources.Res
import d_insurance.composeapp.generated.resources.TravelTime
import d_insurance.composeapp.generated.resources.TripsHistoryDetailsTitle
import d_insurance.composeapp.generated.resources.TripsHistoryTitle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun TripHistory(
    component: TripHistoryComponent,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DinsuranceTopBar(
                text = stringResource(resource = Res.string.TripsHistoryTitle),
                onBackClicked = component::onBackClicked
            )
        },
    ) { padding ->
        val state by component.state.subscribeAsState()
        val details = state.details
        if (details != null) {
            TripBottomSheetDetails(
                item = details.trip,
                onDismiss = {
                    component.onTripClicked(item = null)
                }
            )
        }
        Box(
            modifier = Modifier
                .padding(paddingValues = padding)
                .padding(horizontal = Material3_Dp_16)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(space = Material3_Dp_16)
            ) {
                items(
                    key = { item ->
                        item.id
                    },
                    items = state.tripHistory
                ) { item ->
                    when (item) {
                        is TripHistoryItem.Item -> TripRowItem(
                            item = item,
                            onClick = {
                                component.onTripClicked(item = item)
                            }
                        )

                        is TripHistoryItem.Row -> TripRowHeader(item = item)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TripBottomSheetDetails(
    item: TripHistoryItem.Item,
    onDismiss: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        containerColor = bottomSheetBackground
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Material3_Dp_16)
                .padding(bottom = Material3_Dp_24),
            verticalArrangement = Arrangement.spacedBy(space = Material3_Dp_16)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterStart),
                    text = stringResource(resource = Res.string.TripsHistoryDetailsTitle),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = primaryPaperBadge,
                        textAlign = TextAlign.Center
                    )
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = primaryPaperBadge,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(Material3_Dp_8)
                        .clickable(
                            onClick = {
                                scope.launch {
                                    bottomSheetState.hide()
                                    onDismiss()
                                }
                            }
                        )
                )
            }

            Text(text = item.date, color = primaryPaperBadge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Material3_Dp_8)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(Material3_Dp_12)
                ) {
                    TripHistoryInfoBadge(
                        modifier = Modifier.fillMaxWidth(),
                        name = stringResource(resource = Res.string.AverageSpeed),
                        value = item.details.speed,
                        icon = Icons.Speed
                    )
                    TripHistoryInfoBadge(
                        modifier = Modifier.fillMaxWidth(),
                        name = stringResource(resource = Res.string.FuelConsumption),
                        value = item.details.fuel,
                        icon = Icons.GasStation
                    )
                }
                TripHistoryDoubleInfoBadge(
                    modifier = Modifier.weight(1F).fillMaxWidth(),
                    nameFirst = stringResource(resource = Res.string.Distance),
                    valueFirst = item.details.distance,
                    iconFirst = Icons.History,
                    nameSecond = stringResource(resource = Res.string.TravelTime),
                    valueSecond = item.details.travelTime,
                    iconSecond = Icons.Distance
                )
            }
            Text(
                text = stringResource(resource = Res.string.DrivingScore),
                color = primaryPaperBadge,
                fontSize = 16.sp
            )
            DrivingScoreView(
                item.progress,
                modifier = Modifier
                    .border(
                        width = Stroke_Dp_1, color = primaryGray600,
                        shape = RoundedCornerShape(
                            size = Material3_Dp_16
                        )
                    )
                    .clip(shape = RoundedCornerShape(size = Material3_Dp_16))
                    .padding(all = Material3_Dp_16)
            )
        }
    }
}

@Composable
private fun TripHistoryDoubleInfoBadge(
    modifier: Modifier = Modifier,
    valueFirst: String,
    nameFirst: String,
    iconFirst: ImageVector,
    valueSecond: String,
    nameSecond: String,
    iconSecond: ImageVector,
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = Material3_Dp_16))
            .background(color = primaryGray600.copy(alpha = 0.2f))
            .padding(all = Material3_Dp_16),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = valueFirst,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = primaryPaperBadge,
                    )
                )
                Text(
                    text = nameFirst,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = primaryGray600
                    )
                )
            }
            Icon(
                imageVector = iconFirst,
                contentDescription = null,
                tint = primaryPaperBadge
            )
        }
        Spacer(modifier = Modifier.height(height = Material3_Dp_20))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = Stroke_Dp_1)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = valueSecond,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = primaryPaperBadge,
                    )
                )
                Text(
                    text = nameSecond,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = primaryGray600
                    )
                )
            }
            Icon(
                imageVector = iconSecond,
                contentDescription = null,
                tint = primaryPaperBadge
            )
        }

    }
}

@Composable
private fun TripHistoryInfoBadge(
    modifier: Modifier = Modifier,
    value: String,
    name: String,
    icon: ImageVector,
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = Material3_Dp_16))
            .background(color = primaryGray600.copy(alpha = 0.2f))
            .padding(all = Material3_Dp_16)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = primaryPaperBadge
                    )
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = primaryGray600
                    )
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = primaryPaperBadge
            )
        }

    }
}

@Composable
private fun TripRowHeader(
    item: TripHistoryItem.Row,
) {
    Text(
        text = item.title,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = primaryGray600,
            fontWeight = FontWeight.Bold,
        )
    )
}

@Composable
private fun TripRowItem(
    item: TripHistoryItem.Item,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = Material3_Dp_16))
            .clickable(onClick = onClick),
        colors = ListItemDefaults.colors(
            containerColor = primaryGray600.copy(alpha = 0.2f)
        ),
        overlineContent = {
            Text(
                text = item.date,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = primaryPaperBadge,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        headlineContent = {
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = primaryGray600
                )
            )
        },
        trailingContent = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(space = Material3_Dp_8)
            ) {
                Text(
                    text = item.timestamp,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = primaryGray600,
                        fontWeight = FontWeight.Bold,
                    )
                )
                DrivingScoreViewProgressBar(
                    modifier = Modifier.fillMaxWidth(fraction = .5F),
                    progress = item.progress
                )
            }
        }
    )
}