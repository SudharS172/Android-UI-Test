package com.android.systemui.agentic

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TravelScreen(tripInfo: AgenticUICoordinator.TripInfo) {
    AdaptiveCardLayout.AdaptiveCard(
        contentType = DynamicThemeEngine.ContentType.TRAVEL
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.FlightTakeoff,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Upcoming Trip",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            FlightRouteInfo(
                departureCode = tripInfo.origin,
                arrivalCode = tripInfo.destination,
                departureTime = tripInfo.departureTime,
                arrivalTime = tripInfo.arrivalTime
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /* Handle details */ }) {
                    Text("View Details")
                }
                Button(onClick = { /* Handle check-in */ }) {
                    Text("Check In")
                }
            }
        }
    }
}

@Composable
private fun FlightRouteInfo(
    departureCode: String,
    arrivalCode: String,
    departureTime: String,
    arrivalTime: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(departureCode, style = MaterialTheme.typography.titleMedium)
            Text(departureTime, style = MaterialTheme.typography.bodySmall)
        }

        Icon(
            imageVector = Icons.Default.Flight,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(arrivalCode, style = MaterialTheme.typography.titleMedium)
            Text(arrivalTime, style = MaterialTheme.typography.bodySmall)
        }
    }
}
