package com.mr.codingapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("jsonschema2pojo")
data class Weather (
    @SerializedName("id")
    @Expose
    var id: Int?,

    @SerializedName("main")
    @Expose
    var main_description: String?,

    @SerializedName("description")
    @Expose
    var description: String?,

    @SerializedName("icon")
    @Expose
    var icon: String?,
)

@Generated("jsonschema2pojo")
data class Main(
    @SerializedName("temp")
    @Expose
    var temp: Double?,

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double?,

    @SerializedName("pressure")
    @Expose
    var pressure: Int?,

    @SerializedName("humidity")
    @Expose
    var humidity: Int?,
)

@Generated("jsonschema2pojo")
data class OpenWeatherMapData (
    @SerializedName("timezone")
    @Expose
    var timezone: String?,

    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int?,

    @SerializedName("name")
    @Expose
    var cityName: String?,

    @SerializedName("dt")
    @Expose
    var dt: Long?,

    @SerializedName("main")
    @Expose
    var main: Main?,

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>?,
)
