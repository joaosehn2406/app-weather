package com.jceco.weatherapp.utils

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

val weatherTypography = Typography(

    // Titles
    titleLarge = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = Color.White
    ),
    titleMedium = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color.White
    ),
    titleSmall = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    bodyMedium = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White
    ),
    bodySmall = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = Color.White
    ),

    // Labels
    labelLarge = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.White
    ),
    labelMedium = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.White
    ),
    labelSmall = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        color = Color.White
    ),

    // Displays
    displayLarge = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 100.sp,
        color = Color.White
    ),
    displayMedium = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 36.sp,
        color = Color.White
    ),
    displaySmall = TextStyle(
        fontFamily = figTreeFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        color = Color.White
    ),
)
