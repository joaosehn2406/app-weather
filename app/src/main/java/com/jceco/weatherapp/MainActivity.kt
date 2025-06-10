package com.jceco.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
import com.jceco.weatherapp.pages.WeatherHomeScreen
import com.jceco.weatherapp.viewmodel.WeatherHomeViewModel
import com.jceco.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        SingletonImageLoader.setUnsafe {
            ImageLoader.Builder(this).apply {
                logger(DebugLogger())
            }.build()
        }
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

