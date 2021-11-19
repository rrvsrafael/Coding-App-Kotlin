package com.mr.codingapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDatabaseDao {

    @Insert
    fun insert(measuring: WeatherMeasuring)

    @Query("SELECT * from weather_data_table WHERE measuring_time = :key")
    fun get(key: Long): WeatherMeasuring?

    @Query("SELECT * FROM weather_data_table ORDER BY measuring_time DESC LIMIT 1")
    fun getLastWeather(): WeatherMeasuring?

    @Query("SELECT * FROM weather_data_table ORDER BY measuring_time DESC")
    fun getAllNights(): LiveData<List<WeatherMeasuring>>

    @Query("DELETE FROM weather_data_table WHERE measuring_time = (SELECT MIN(measuring_time) FROM weather_data_table)")
    fun removeOldestNight(): Void

    @Query("SELECT Count(*) FROM weather_data_table")
    fun numberOfEntries(): Int
}