package com.jceco.weatherapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.jceco.weatherapp.pages.ConnectivityState
import com.jceco.weatherapp.pages.WeatherHomeScreen
import com.jceco.weatherapp.pages.WeatherHomeUiState
import com.jceco.weatherapp.ui.theme.WeatherAppTheme
import com.jceco.weatherapp.viewmodel.WeatherHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = LocationServices.getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent { WeatherApp(client) }
    }
}

@Composable
fun WeatherApp(client: FusedLocationProviderClient) {
    val vm: WeatherHomeViewModel = viewModel()
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        permissionGranted = it
    }
    LaunchedEffect(Unit) {
        val fineLoc = android.Manifest.permission.ACCESS_FINE_LOCATION
        if (ContextCompat.checkSelfPermission(context, fineLoc) != PackageManager.PERMISSION_GRANTED) {
            launcher.launch(fineLoc)
        } else {
            permissionGranted = true
        }
    }
    LaunchedEffect(permissionGranted) {
        if (!permissionGranted) return@LaunchedEffect
        val cts = CancellationTokenSource()
        client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cts.token)
            .addOnSuccessListener { loc ->
                if (loc != null) {
                    vm.setLocation(loc.latitude, loc.longitude)
                    vm.getWeatherData()
                } else {
                    vm.uiState = WeatherHomeUiState.Error
                }
            }
            .addOnFailureListener {
                vm.uiState = WeatherHomeUiState.Error
            }
    }
    val connectivityState by vm.connectivityState.collectAsState(initial = ConnectivityState.Unavailable)
    val isConnected = connectivityState == ConnectivityState.Available
    WeatherAppTheme {
        WeatherHomeScreen(
            isConnected = isConnected,
            onRefresh = {
                vm.uiState = WeatherHomeUiState.Loading
                vm.getWeatherData()
            },
            uiState = vm.uiState
        )
    }
}
