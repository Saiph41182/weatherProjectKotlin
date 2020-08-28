package com.example.firstproject41182.util

import android.text.format.DateFormat
import android.widget.ImageView
import com.example.firstproject41182.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun ImageView.getImageByUrl(url: String){
    val picasso = Picasso.get()
    picasso.isLoggingEnabled = true

    picasso.load(url)
        .placeholder(R.drawable.ic_weather_image_placeholder)
        .error(R.drawable.ic_weather_image_error)
        .into(this)


}

fun Long.toDdMmFormat(format: String): String{
    val cal = Calendar.getInstance()
    val tz = cal.timeZone
    val sdf = SimpleDateFormat(format,Locale.getDefault())
    sdf.timeZone = tz
    return sdf.format(Date(this * 1000))
}

fun Double.toCelsius(): String{
    return (this - 273.15).roundToInt().toString()
}

fun String.provideImage(): Int{
    return when(this){
        "01n","01d" -> R.drawable.ic_01d
        "02n","02d" -> R.drawable.ic_02d
        "03n","03d" -> R.drawable.ic_03d
        "04n","04d" -> R.drawable.ic_04d
        "09n","09d" -> R.drawable.ic_09d
        "10n","10d" -> R.drawable.ic_10d
        "11n","11d" -> R.drawable.ic_11d
        "13n","13d" -> R.drawable.ic_13d
        "50n","50d" -> R.drawable.ic_50d
        else -> R.drawable.ic_weather_image_error
    }
}

