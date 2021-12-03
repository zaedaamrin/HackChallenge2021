package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class PlayingASongActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var songPicture: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_asong)

        backButton = findViewById(R.id.backButton)
        songPicture = findViewById(R.id.songPicture)
        val position = intent.extras?.getInt("position")

        backButton.setOnClickListener {
            val intent = Intent(this, PlaylistSongsActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(intent)
        }

        songPicture.setImageResource(R.drawable.empty_playlist)
    }
}