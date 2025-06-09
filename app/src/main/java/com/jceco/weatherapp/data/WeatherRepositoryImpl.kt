package com.jceco.weatherapp.data

import com.jceco.weatherapp.services.WeatherApi
import com.jceco.weatherapp.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {

    override suspend fun getCurrentWeather(endUrl: String): CurrentWeather {
        return WeatherApi.retrofitService.getCurrentWeather(endUrl)
    }

    override suspend fun getForecastWeather(endUrl: String): ForecastWeather {
        return WeatherApi.retrofitService.getForecastWeather(endUrl)
    }

}