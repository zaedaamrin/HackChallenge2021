package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlaylistDetailsActivity : AppCompatActivity() {
    private lateinit var buttonToHome : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist_details)

        // get extras from intent and store them in vals
        buttonToHome = findViewById(R.id.buttonToHome)

        buttonToHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
//                .apply {
//                put extras to send to home page
//            }
            startActivity(intent)
        }
    }
}