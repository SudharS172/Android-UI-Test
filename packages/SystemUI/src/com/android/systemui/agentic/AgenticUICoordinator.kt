package com.android.systemui.agentic

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.android.systemui.agentic.ExampleTravelScreen
import com.android.systemui.agentic.ExampleWeatherScreen

class AgenticUICoordinator(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) {
    private val themeEngine = DynamicThemeEngine(context)
    private val _uiState = MutableStateFlow<UIState>(UIState.Default)
    val uiState: StateFlow<UIState> = _uiState

    sealed class UIState {
        object Default : UIState()
        data class Travel(
            val tripInfo: TripInfo,
            val weatherInfo: WeatherInfo? = null
        ) : UIState()
        data class Collection(
            val items: List<CollectionItem>,
            val layout: CollectionLayout = CollectionLayout.GRID
        ) : UIState()
        data class Assistant(
            val query: String,
            val suggestions: List<String>
        ) : UIState()
        data class Weather(val info: WeatherInfo) : UIState()
    }

    data class TripInfo(
        val origin: String,
        val destination: String,
        val departureTime: String,
        val arrivalTime: String,
        val terminal: String,
        val gate: String
    )

    enum class CollectionLayout {
        GRID, LIST, CAROUSEL
    }

    @Composable
    fun AgenticHomeScreen() {
        val currentState by uiState.collectAsState()
        
        DynamicBackground(
            contentType = when (currentState) {
                is UIState.Travel -> DynamicThemeEngine.ContentType.TRAVEL
                is UIState.Collection -> DynamicThemeEngine.ContentType.GENERAL
                is UIState.Weather -> DynamicThemeEngine.ContentType.WEATHER
                else -> DynamicThemeEngine.ContentType.GENERAL
            }
        ) {
            when (val state = currentState) {
                is UIState.Travel -> ExampleTravelScreen.TravelScreen(state.tripInfo)
                is UIState.Collection -> CollectionGrid(state.items)
                is UIState.Assistant -> AssistantView(state.query, state.suggestions)
                is UIState.Weather -> ExampleWeatherScreen.WeatherScreen(state.info)
                else -> DefaultHome()
            }
        }
    }

    @Composable
    private fun DynamicBackground(
        contentType: DynamicThemeEngine.ContentType,
        content: @Composable () -> Unit
    ) {
        val themeState = themeEngine.generateThemeForContent(
            contentType = contentType,
            timeOfDay = getCurrentHour()
        )
        
        themeEngine.DynamicTheme(themeState) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(themeState.primaryGradient)
            ) {
                content()
            }
        }
    }

    fun updateState(newState: UIState) {
        _uiState.value = newState
    }

    private fun getCurrentHour(): Int {
        return java.time.LocalTime.now().hour
    }
}
