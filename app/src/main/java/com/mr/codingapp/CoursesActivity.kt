package com.mr.codingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton

class CoursesActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_courses)

        val btBack = findViewById<ImageButton>(R.id.courses_back_button)

        btBack.setOnClickListener {
            finish()
        }
    }
}
