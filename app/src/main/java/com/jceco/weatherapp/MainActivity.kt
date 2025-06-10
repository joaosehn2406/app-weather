package com.jceco.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jceco.weatherapp.pages.WeatherHomeScreen
import com.jceco.weatherapp.viewmodel.WeatherHomeViewModel
import com.jceco.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    val weatherHomeViewModel: WeatherHomeViewModel = viewModel()
    weatherHomeViewModel.getWeatherData()
    WeatherAppTheme {
        WeatherHomeScreen(weatherHomeViewModel.uiState)
    }
}

