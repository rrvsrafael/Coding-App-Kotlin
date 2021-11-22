package com.mr.codingapp

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.mr.codingapp.util.LocationPermission

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrentWeatherActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_current_weather)

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
                    //City name
                    val cityName = findViewById<TextView>(R.id.weathernow_city_name)
                    cityName.text = response.body()?.cityName

                    //Icon
                    val weatherIcon = findViewById<View>(R.id.weathernow_icon) as ImageView
                    Glide.with(this@CurrentWeatherActivity)
                        .load("https://openweathermap.org/img/wn/%s@4x.png".format(response.body()?.weather?.get(0)?.icon))
                        .into(weatherIcon)

                    //Description
                    val description = findViewById<TextView>(R.id.weathernow_description)
                    description.text = response.body()?.weather?.get(0)?.main_description

                    //Temperature
                    val temp = findViewById<TextView>(R.id.weathernow_temp)
                    "%.1f °C".format(response.body()?.main?.temp).also {
                        temp.text = it
                    }

                    //Humidity
                    val humidity = findViewById<TextView>(R.id.weathernow_humidity)
                    "Humidity: %d%%".format(response.body()?.main?.humidity).also {
                        humidity.text = it
                    }
                }

                override fun onFailure(call: Call<OpenWeatherMapData?>, t: Throwable) {
                    val cityName = findViewById<TextView>(R.id.weathernow_city_name)
                    "Error getting weather details at OpenWeatherMapData".also {
                        cityName.text = it
                    }
                    throw t
                }
            }
        )
    }

    override fun onLocationChanged(location: Location) {
        getWeather(location.latitude, location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
}