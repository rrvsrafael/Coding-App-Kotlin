package com.mr.codingapp

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        val btStart = findViewById<Button>(R.id.start_button)

        btStart.setOnClickListener {
            val welcomeListActivityIntent = Intent(this, WelcomeListActivity::class.java)
            startActivity(welcomeListActivityIntent)
        }
    }
}