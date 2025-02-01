package com.android.systemui.agentic

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdaptiveCard(
    modifier: Modifier = Modifier,
    contentType: DynamicThemeEngine.ContentType,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 280.dp else 180.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(16.dp)
    ) {
        // Frosted glass effect background
        FrostedGlassBackground(
            alpha = 0.95f,
            blur = 10.dp
        )
        
        // Dynamic content
        content()
        
        // Interactive elements
        when (contentType) {
            DynamicThemeEngine.ContentType.TRAVEL -> TravelCardContent()
            DynamicThemeEngine.ContentType.WEATHER -> WeatherCardContent()
            else -> DefaultCardContent()
        }
    }
}

@Composable
private fun FrostedGlassBackground(
    alpha: Float,
    blur: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = alpha),
                        Color.White.copy(alpha = alpha * 0.95f)
                    )
                )
            )
    )
}

@Composable
private fun TravelCardContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Flight info layout similar to the screenshot
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Incoming Trip",
                style = MaterialTheme.typography.titleMedium
            )
            
            Icon(
                imageVector = Icons.Default.FlightTakeoff,
                contentDescription = null
            )
        }
        
        // Flight details
        FlightRouteInfo(
            departure = "SFO",
            arrival = "JFK",
            departureTime = "9:25 PM",
            arrivalTime = "4:30 AM"
        )
    }
}

@Composable
private fun WeatherCardContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherInfo(
            temperature = "26°",
            condition = "Partly cloudy"
        )
        
        WeatherIcon(
            condition = WeatherCondition.PARTLY_CLOUDY
        )
    }
}
