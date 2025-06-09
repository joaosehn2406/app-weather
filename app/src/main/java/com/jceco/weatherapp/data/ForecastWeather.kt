package com.jceco.weatherapp.data
import com.google.gson.annotations.SerializedName

data class ForecastWeather(
    @SerializedName("city")
    val city: City = City(),
    @SerializedName("cnt")
    val cnt: Int = 0,
    @SerializedName("cod")
    val cod: String = "",
    @SerializedName("list")
    val list: List<ForecastItem> = listOf(),
    @SerializedName("message")
    val message: Int = 0
) {
    data class City(
        @SerializedName("coord")
        val coord: Coord = Coord(),
        @SerializedName("country")
        val country: String = "",
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("population")
        val population: Int = 0,
        @SerializedName("sunrise")
        val sunrise: Int = 0,
        @SerializedName("sunset")
        val sunset: Int = 0,
        @SerializedName("timezone")
        val timezone: Int = 0
    ) {
        data class Coord(
            @SerializedName("lat")
            val lat: Double = 0.0,
            @SerializedName("lon")
            val lon: Double = 0.0
        )
    }

    data class ForecastItem(
        @SerializedName("clouds")
        val clouds: Clouds = Clouds(),
        @SerializedName("dt")
        val dt: Int = 0,
        @SerializedName("dt_txt")
        val dtTxt: String = "",
        @SerializedName("main")
        val main: Main = Main(),
        @SerializedName("pop")
        val pop: Double = 0.0,
        @SerializedName("rain")
        val rain: Rain? = null,
        @SerializedName("sys")
        val sys: Sys = Sys(),
        @SerializedName("visibility")
        val visibility: Int = 0,
        @SerializedName("weather")
        val weather: List<Weather> = listOf(),
        @SerializedName("wind")
        val wind: Wind = Wind()
    ) {
        data class Clouds(
            @SerializedName("all")
            val all: Int = 0
        )

        data class Main(
            @SerializedName("feels_like")
            val feelsLike: Double = 0.0,
            @SerializedName("grnd_level")
            val grndLevel: Int = 0,
            @SerializedName("humidity")
            val humidity: Int = 0,
            @SerializedName("pressure")
            val pressure: Int = 0,
            @SerializedName("sea_level")
            val seaLevel: Int = 0,
            @SerializedName("temp")
            val temp: Double = 0.0,
            @SerializedName("temp_kf")
            val tempKf: Double = 0.0,
            @SerializedName("temp_max")
            val tempMax: Double = 0.0,
            @SerializedName("temp_min")
            val tempMin: Double = 0.0
        )

        data class Rain(
            @SerializedName("3h")
            val h: Double = 0.0
        )

        data class Sys(
            @SerializedName("pod")
            val pod: String = ""
        )

        data class Weather(
            @SerializedName("description")
            val description: String = "",
            @SerializedName("icon")
            val icon: String = "",
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("main")
            val main: String = ""
        )

        data class Wind(
            @SerializedName("deg")
            val deg: Int = 0,
            @SerializedName("gust")
            val gust: Double = 0.0,
            @SerializedName("speed")
            val speed: Double = 0.0
        )
    }
}


