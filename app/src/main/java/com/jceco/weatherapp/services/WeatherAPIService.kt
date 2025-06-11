package com.jceco.weatherapp.services

import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherApiService {

    @GET()
    suspend fun getCurrentWeather(@Url endUrl: String) : CurrentWeather

    @GET()
    suspend fun getForecastWeather(@Url endUrl: String) : ForecastWeather
}


