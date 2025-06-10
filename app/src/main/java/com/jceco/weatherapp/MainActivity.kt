package com.jceco.weatherapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jceco.weatherapp.pages.WeatherHomeScreen
import com.jceco.weatherapp.ui.theme.WeatherAppTheme
import com.jceco.weatherapp.viewmodel.WeatherHomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client: FusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent {
            WeatherApp(client)
        }
    }
}

@Composable
fun WeatherApp(
    client: FusedLocationProviderClient,
    modifier: Modifier = Modifier
) {
    val weatherHomeViewModel: WeatherHomeViewModel = viewModel()
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted = granted
    }
    LaunchedEffect(Unit) {
        val isPermissionGranted = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if(!isPermissionGranted) {
            launcher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            permissionGranted = true
        }
    }

    LaunchedEffect(permissionGranted) {
        if(permissionGranted) {
            client.lastLocation.addOnSuccessListener {
                weatherHomeViewModel.setLocation(it.latitude, it.longitude)
                weatherHomeViewModel.getWeatherData()
            }
        }
    }
    weatherHomeViewModel.getWeatherData()
    WeatherAppTheme {
        WeatherHomeScreen(weatherHomeViewModel.uiState)
    }
}

