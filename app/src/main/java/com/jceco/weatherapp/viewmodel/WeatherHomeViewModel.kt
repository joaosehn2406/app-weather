package com.jceco.weatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather
import com.jceco.weatherapp.data.WeatherRepositoryImpl
import com.jceco.weatherapp.pages.Weather
import com.jceco.weatherapp.pages.WeatherHomeUiState
import com.jceco.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherHomeViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    var uiState: WeatherHomeUiState by mutableStateOf(WeatherHomeUiState.Loading)

    val exceptionHandler = CoroutineExceptionHandler { _ , _ ->
        uiState = WeatherHomeUiState.Error
    }

    fun getWeatherData(){
        viewModelScope.launch(exceptionHandler) {
            uiState = try {
                val currentWeather = async { getCurrentData() }.await()
                val forecastWeather = async { getForecastData() }.await()
//                Log.d("WeatherHomeViewMOdel", "currentData: ${currentWeather.main!!.temp}")
//                Log.d("WeatherHomeViewMOdel", "currentData: ${forecastWeather.list.size}")
                WeatherHomeUiState.Success(Weather(currentWeather, forecastWeather))
            }
            catch(e: Exception) {
                WeatherHomeUiState.Error
            }
        }
    }

    private suspend fun getCurrentData() : CurrentWeather {
        val endUrl: String = "weather?lat=-26.8975&lon=-49.2317&appid=c3ca9fa126f4a03f93b7b2001c14a0c8"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData() : ForecastWeather {
        val endUrl: String = "forecast?lat=-26.8975&lon=-49.2317&appid=c3ca9fa126f4a03f93b7b2001c14a0c8"
        return weatherRepository.getForecastWeather(endUrl)
    }
}