package com.mr.codingapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationPermission {

    private val locationPermissionCode = 42

    fun getLocation(context: Context, activity: Activity, locationListener: LocationListener): LocationManager? {
        var locationManager : LocationManager? = null
        if (checkPermissions(context)) {
            if (isLocationEnabled(activity)) {
                locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if ((ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED)
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        locationPermissionCode
                    )
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, locationListener)
            }
            else {
                Toast.makeText(context, "Turn on location", Toast.LENGTH_LONG).show()
            }
        }
        else {
            requestPermissions(activity)
        }
        return locationManager
    }

    private fun checkPermissions(context: Context): Boolean {
        if (
            ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            locationPermissionCode
        )
    }
    private fun isLocationEnabled(activity: Activity): Boolean {
        var locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
}