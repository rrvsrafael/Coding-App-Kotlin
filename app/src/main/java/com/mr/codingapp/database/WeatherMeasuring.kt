package com.mr.codingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data_table")
data class WeatherMeasuring(
    @ColumnInfo(name = "city")
    var city: String = "",
    @ColumnInfo(name = "weather_description")
    var description: String = "",
    //var icon:
    @ColumnInfo(name = "temperature_measured")
    var temperature: Double = -300.0,
    @PrimaryKey(autoGenerate = false)
    var measuring_time: Long = -1
)
