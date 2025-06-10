package com.jceco.weatherapp.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jceco.weatherapp.R
import com.jceco.weatherapp.customui.AppBackground

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
                    title = { Text(text = "Weather App", style = MaterialTheme.typography.titleLarge) },
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
                Text(text = "Weather Home Screen", style = MaterialTheme.typography.displaySmall)
            }
        }
    }
}