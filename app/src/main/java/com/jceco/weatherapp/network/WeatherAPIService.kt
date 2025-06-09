package com.jceco.weatherapp.network

import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface WeatherApiService {

    @GET("weather?lat=26.927297526419704&lon=-49.25099473192019&appid=c3ca9fa126f4a03f93b7b2001c14a0c8")
    suspend fun getCurrentWeather() : CurrentWeather

    @GET("forecast?lat=26.927297526419704&lon=-49.25099473192019&appid=c3ca9fa126f4a03f93b7b2001c14a0c8")
    suspend fun getForecastWeather() : ForecastWeather
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}

