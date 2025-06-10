package com.jceco.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedDate(dt: Int, pattern: String = "dd/MM/yyyy"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(dt.toLong() * 1000))
}

fun getIconURL(icon: String) : String {
    return "https://openweathermap.org/img/wn/$icon@2x.png"
}
