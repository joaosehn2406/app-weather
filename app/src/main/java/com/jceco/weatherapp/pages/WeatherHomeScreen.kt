package com.jceco.weatherapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.jceco.weatherapp.data.ForecastWeather
import com.jceco.weatherapp.utils.DEGREE
import com.jceco.weatherapp.utils.getFormattedDate
import com.jceco.weatherapp.utils.getIconUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHomeScreen(
    isConnected: Boolean,
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
                if (!isConnected) {
                    Text("No internet connection", style = MaterialTheme.typography.titleMedium)
                } else {
                    when (uiState) {
                        is WeatherHomeUiState.Loading -> Text(text = "Loading...")
                        is WeatherHomeUiState.Error -> Text(text = "Failed to fetch data")
                        is WeatherHomeUiState.Success -> WeatherSection(weather = uiState.weather)
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorSection(
    msg: String,
    onRefresh: () -> Unit
) {
    Column {
        Text(msg)
        Spacer(modifier = Modifier.height(12.dp))
        IconButton(
            onClick = onRefresh,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.White)
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
        ForecastWeatherSection(forecastItems = weather.forecastWeather.list)
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
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            getFormattedDate(currentWeather.dt, pattern = "dd/MM/yyyy"),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "${"%.1f".format((currentWeather.main.temp.toDouble() - 100) / 10)}$DEGREE",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "${currentWeather.main.feelsLike.toInt()}$DEGREE",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getIconUrl(currentWeather.weather[0].icon))
                    .crossfade(true)
                    .build(),
                contentDescription = "Ícone do clima",
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = currentWeather.weather.firstOrNull()?.description
                    ?.replaceFirstChar { it.uppercaseChar() } ?: "Sem descrição",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Humidity ${currentWeather.main.humidity}%",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Pressure ${currentWeather.main.pressure}Pa",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Visibility ${currentWeather.main.humidity}%",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Surface(
                modifier = Modifier
                    .width(2.dp)
                    .height(100.dp)
            ) {}
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Sunrise ${getFormattedDate(currentWeather.sys.sunrise, "HH:mm")}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Sunset ${getFormattedDate(currentWeather.sys.sunset, "HH:mm")}",
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }
    }

}

@Composable
fun ForecastWeatherSection(
    forecastItems: List<ForecastWeather.ForecastItem>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(forecastItems.size) { index ->
            ForecastWeatherItem(forecastItems[index])
        }
    }
}

@Composable
fun ForecastWeatherItem(
    item: ForecastWeather.ForecastItem,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.5f)),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(4.dp)
        ) {
            Text(
                getFormattedDate(item.dt, pattern = "EEE"),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                getFormattedDate(item.dt, pattern = "HH:mm"),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = modifier.height(10.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getIconUrl(item.weather[0].icon))
                    .crossfade(true)
                    .build(),
                contentDescription = "Ícone do clima",
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                "${"%.1f".format((item.main.temp.toDouble() - 100) / 10)}$DEGREE",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}