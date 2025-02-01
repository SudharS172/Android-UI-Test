package com.android.systemui.agentic

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherScreen(weatherInfo: WeatherInfo) {
    AdaptiveCardLayout.AdaptiveCard(
        contentType = DynamicThemeEngine.ContentType.WEATHER
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
                    imageVector = Icons.Default.Cloud,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Current Weather",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(weatherInfo.temperature, style = MaterialTheme.typography.displayMedium)
                    Text(weatherInfo.condition, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherDetail(icon = Icons.Default.WaterDrop, label = "Humidity", value = weatherInfo.humidity)
                WeatherDetail(icon = Icons.Default.Air, label = "Wind", value = weatherInfo.windSpeed)
            }
        }
    }
}

@Composable
private fun WeatherDetail(icon: ImageVector, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = label, modifier = Modifier.size(24.dp))
        Text(text = label, style = MaterialTheme.typography.bodySmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

data class WeatherInfo(
    val temperature: String,
    val condition: String,
    val humidity: String,
    val windSpeed: String
)
