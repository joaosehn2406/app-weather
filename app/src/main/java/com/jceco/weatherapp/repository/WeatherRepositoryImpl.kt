package com.jceco.weatherapp.repository

import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather
import com.jceco.weatherapp.services.WeatherApi
import com.jceco.weatherapp.services.WeatherApiService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getCurrentWeather(endUrl: String): CurrentWeather {
        return weatherApiService.getCurrentWeather(endUrl)
    }

    override suspend fun getForecastWeather(endUrl: String): ForecastWeather {
        return weatherApiService.getForecastWeather(endUrl)
    }

}