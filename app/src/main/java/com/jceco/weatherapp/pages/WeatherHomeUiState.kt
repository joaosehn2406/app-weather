package com.jceco.weatherapp.pages

import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather

data class Weather(
    val currentWeather: CurrentWeather,
    val forecastWeather: ForecastWeather
)

sealed interface WeatherHomeUiState {
    data class Success(val weather: Weather) : WeatherHomeUiState
    data object Error : WeatherHomeUiState
    data object Loading : WeatherHomeUiState
}
