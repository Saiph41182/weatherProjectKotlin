package com.example.firstproject41182.buisness.api

import com.example.firstproject41182.buisness.model.WeatherData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("2.5/onecall?")
    fun getDailyForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String = "ca8d1939be2fc1f8cc73a2e515d9ad1f"
    ): Observable<WeatherData>

    enum class ExcludedParts(val value: String) {
        CURRENT("current"), MINUTELY("minutely"), HOURLY("hourly"), DAILY("daily");

        companion object {
            fun excludeAllExceptOne(one: ExcludedParts): String {
                return excludeThem(one)
            }

            fun excludeThem(vararg items: ExcludedParts): String {
                return items
                    .toSet()
                    .joinToString(",") { it.value }
            }
        }
    }
}