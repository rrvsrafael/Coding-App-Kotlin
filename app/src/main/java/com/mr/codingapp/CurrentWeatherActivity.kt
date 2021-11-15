package com.mr.codingapp

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng

import com.mr.codingapp.util.LocationPermission

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrentWeatherActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weather)

//        val iconUrl = "https://openweathermap.org/img/wn/${icon}@4x.png";

        val weatherIcon = findViewById<View>(R.id.weather_icon) as ImageView

        Glide.with(this)
            .load("https://openweathermap.org/img/wn/01n@4x.png")
            .into(weatherIcon)

        val btBack = findViewById<ImageButton>(R.id.weathernow_back_button)

        btBack.setOnClickListener {
            finish()
        }
        getLocation()
    }

    private fun getLocation() {
        val l = LocationPermission()
        locationManager = l.getLocation(this, this, this)!!
    }

    private fun getWeather(lat: Double, lon: Double) {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.openweathermap.org/data/2.5/")

        val endpoint = retrofitClient.create(OpenWeatherMapService::class.java)
        val callback = endpoint.openWeatherMapData(lat.toString(), lon.toString())

        callback?.enqueue(
            object : Callback<OpenWeatherMapData?> {
                override fun onResponse(
                    call: Call<OpenWeatherMapData?>,
                    response: Response<OpenWeatherMapData?>
                ) {
                    val cityName = findViewById<TextView>(R.id.city_name)
                    cityName.text = response.body()?.cityName
                }

                override fun onFailure(call: Call<OpenWeatherMapData?>, t: Throwable) {
                    val cityName = findViewById<TextView>(R.id.city_name)
                    cityName.text = "Error getting weather details at OpenWeatherMapData"
                }
            }
        )
    }

    override fun onLocationChanged(location: Location) {
        getWeather(location.latitude, location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
}