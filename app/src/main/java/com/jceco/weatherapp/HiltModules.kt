package com.jceco.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import com.jceco.weatherapp.repository.ConnectivityRepository
import com.jceco.weatherapp.repository.DefaultConnectivityRepository
import com.jceco.weatherapp.repository.WeatherRepository
import com.jceco.weatherapp.repository.WeatherRepositoryImpl
import com.jceco.weatherapp.services.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    fun provideRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherAPIService(retrofit: Retrofit) : WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context) : ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }

    @Provides
    fun providateConnectivityRepository(connectivityManager: ConnectivityManager) : ConnectivityRepository {
        return DefaultConnectivityRepository(connectivityManager)
    }

    @Provides
    fun providaWeatherRepository(weatherApiService: WeatherApiService) : WeatherRepository {
        return WeatherRepositoryImpl(weatherApiService)
    }


}

