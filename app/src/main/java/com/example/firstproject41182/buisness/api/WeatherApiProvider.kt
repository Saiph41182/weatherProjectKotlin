package com.example.firstproject41182.buisness.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WeatherApiProvider {
    val weatherApiService: WeatherApi by lazy{ create() }

    private fun create(): WeatherApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/")
            .build()
        return retrofit.create(WeatherApi::class.java)
    }

}