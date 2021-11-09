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
    var main: String?,

    @SerializedName("description")
    @Expose
    var description: String?,

    @SerializedName("icon")
    @Expose
    var icon: String?,
)

@Generated("jsonschema2pojo")
data class Current(
    @SerializedName("dt")
    @Expose
    var dt: Int?,

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

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double?,

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>?,
)

@Generated("jsonschema2pojo")
data class OpenWeatherMapData (
    @SerializedName("timezone")
    @Expose
    var timezone: String?,

    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int?,

    @SerializedName("current")
    @Expose
    var current: Current?,
)
