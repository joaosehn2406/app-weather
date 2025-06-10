package com.jceco.weatherapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jceco.weatherapp.R
import com.jceco.weatherapp.customui.AppBackground
import com.jceco.weatherapp.data.CurrentWeather
import com.jceco.weatherapp.utils.DEGREE
import com.jceco.weatherapp.utils.getFormattedDate
import com.jceco.weatherapp.utils.getIconURL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHomeScreen(
    uiState: WeatherHomeUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AppBackground(photoId = R.drawable.beautiful_skyscape_daytime)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Weather App",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        actionIconContentColor = Color.White,
                    )

                )
            },
            containerColor = Color.Transparent
        ) {
            Surface(
                color = Color.Transparent,
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize()
            ) {
                when (uiState) {
                    is WeatherHomeUiState.Loading -> Text(text = "Carregando...")
                    is WeatherHomeUiState.Error -> Text(text = "Erro ao buscar dados")
                    is WeatherHomeUiState.Success -> WeatherSection(weather = uiState.weather)
                }
            }
        }
    }
}

@Composable
fun WeatherSection(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        CurrentWeatherSection(
            currentWeather = weather.currentWeather,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CurrentWeatherSection(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${currentWeather.name} | ${currentWeather.sys.country}",
            style = MaterialTheme.typography.titleMedium)
        Text(
            getFormattedDate(currentWeather.dt, pattern = "dd/MM/yyyy"),
            style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "${currentWeather.main.temp.toInt()}$DEGREE",
            style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "${currentWeather.main.feelsLike.toInt()}$DEGREE",
            style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = currentWeather.weather.firstOrNull()?.icon ?: ""
            val iconUrl = getIconURL(icon)

            Log.d("WeatherApp", "Icon URL: $iconUrl")

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(iconUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Ícone do clima",
                modifier = Modifier.size(48.dp),
                onError = {
                    Log.e("WeatherApp", "Erro ao carregar imagem: ${it.result.throwable}")
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = currentWeather.weather.firstOrNull()?.description
                    ?.replaceFirstChar { it.uppercaseChar() } ?: "Sem descrição",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
    Log.d("WeatherApp", "Icon URL: ${getIconURL(currentWeather.weather.firstOrNull()?.icon ?: "vazio")}")

}