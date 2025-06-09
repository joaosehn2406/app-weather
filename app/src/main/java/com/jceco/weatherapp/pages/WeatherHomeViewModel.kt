package com.jceco.weatherapp.pages

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather
import com.jceco.weatherapp.data.WeatherRepositoryImpl
import com.jceco.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherHomeViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()

    fun getWeatherData(){
        viewModelScope.launch {
            try {
                val currentWeather = async { getCurrentData() }.await()
                val forecastWeather = async { getForecastData() }.await()
                Log.d("WeatherHomeViewMOdel", "currentData: ${currentWeather.main!!.temp}")
            }
            catch(e: Exception) {

            }
        }
    }

    private suspend fun getCurrentData() : CurrentWeather {
        val endUrl: String = "weather?lat=26.927297526419704&lon=-49.25099473192019&appid=c3ca9fa126f4a03f93b7b2001c14a0c8"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData() : ForecastWeather {
        val endUrl: String = "forecast?lat=26.927297526419704&lon=-49.25099473192019&appid=c3ca9fa126f4a03f93b7b2001c14a0c8"
        return weatherRepository.getForecastWeather(endUrl)
    }
}