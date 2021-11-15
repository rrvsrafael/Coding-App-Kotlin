package com.mr.codingapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenWeatherMapService {
    
    @GET("weather")
    fun openWeatherMapData(
        @Query("lat") lat : String?,
        @Query("lon") lon : String?,
        @Query("units") units : String?="metric",
        @Query("appid") appid : String?="d4fc9b2bac857d0afa0cf5a3693cce30"
        ): Call<OpenWeatherMapData?>?
}
