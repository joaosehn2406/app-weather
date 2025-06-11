package com.jceco.weatherapp.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.decode.StaticImageDecoder
import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.data.ForecastWeather
import com.jceco.weatherapp.data.WeatherRepositoryImpl
import com.jceco.weatherapp.pages.ConnectivityState
import com.jceco.weatherapp.pages.Weather
import com.jceco.weatherapp.pages.WeatherHomeUiState
import com.jceco.weatherapp.repository.ConnectivityRepository
import com.jceco.weatherapp.repository.DefaultConnectivityRepository
import com.jceco.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.cache.CacheStrategy

class WeatherHomeViewModel(
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    private var latitude = 0.0
    private var longitude = 0.0
    val connectivityState: StateFlow<ConnectivityState> = connectivityRepository.connectivityState
    var uiState: WeatherHomeUiState by mutableStateOf(WeatherHomeUiState.Loading)

    val exceptionHandler = CoroutineExceptionHandler { _ , _ ->
        uiState = WeatherHomeUiState.Error
    }

    fun setLocation(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
    }

    fun getWeatherData(){
        viewModelScope.launch(exceptionHandler) {
            uiState = try {
                val currentWeather = async { getCurrentData() }.await()
                val forecastWeather = async { getForecastData() }.await()
                Log.d("WeatherHomeViewMOdel", "currentData: ${currentWeather.main!!.temp}")
                Log.d("WeatherHomeViewMOdel", "currentData: ${forecastWeather.list.size}")
                WeatherHomeUiState.Success(Weather(currentWeather, forecastWeather))
            }
            catch(e: Exception) {
                Log.e("WeatherViewModel", "Erro ao buscar dados: ${e.message}", e)
                WeatherHomeUiState.Error
            }
        }
    }

    private suspend fun getCurrentData() : CurrentWeather {
        val endUrl: String = "weather?lat=$latitude&lon=$longitude&appid=c3ca9fa126f4a03f93b7b2001c14a0c8"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData() : ForecastWeather {
        val endUrl: String = "forecast?lat=$latitude&lon=$longitude&appid=c3ca9fa126f4a03f93b7b2001c14a0c8"
        return weatherRepository.getForecastWeather(endUrl)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
                WeatherHomeViewModel(
                    connectivityRepository = DefaultConnectivityRepository(connectivityManager)
                )
            }
        }
    }
}