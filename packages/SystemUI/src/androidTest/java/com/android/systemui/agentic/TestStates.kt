package com.android.systemui.agentic

object TestStates {
    val travelState = UIState.Travel(
        TripInfo(
            origin = "SFO",
            destination = "JFK",
            departureTime = "2023-10-15 09:25",
            arrivalTime = "2023-10-15 16:30"
        )
    )
    
    val weatherState = UIState.Weather(
        WeatherInfo(
            temperature = "26°C",
            condition = "Partly Cloudy",
            humidity = "65%",
            windSpeed = "12 km/h"
        )
    )
}
