package com.mr.codingapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mr.codingapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        testeCospeClima()
    }

    private fun testeCospeClima() {
        val lat = 11.0
        val lon = 12.0

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
                    val tvCentral = findViewById<TextView>(R.id.tvCentral)
                    tvCentral.text = response.body().toString()
                }

                override fun onFailure(call: Call<OpenWeatherMapData?>, t: Throwable) {
                    val tvCentral = findViewById<TextView>(R.id.tvCentral)
                    tvCentral.text = "Deu merda"
                }
            }
        )
    }
}
