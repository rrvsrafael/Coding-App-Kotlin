package com.mr.codingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class WelcomeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_welcome_list)

        val btCourses = findViewById<Button>(R.id.courses_button)
        val btMaps = findViewById<Button>(R.id.maps_button)

        btCourses.setOnClickListener {
            val coursesActivityIntent = Intent(this, CoursesActivity::class.java)
            startActivity(coursesActivityIntent)
        }

        btMaps.setOnClickListener {
            val mapsActivityIntent = Intent(this, MapsActivity::class.java)
            startActivity(mapsActivityIntent)
        }
    }
}