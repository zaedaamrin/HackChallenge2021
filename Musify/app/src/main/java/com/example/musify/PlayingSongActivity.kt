package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class PlayingSongActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var songPlayer: YouTubePlayerView
    private lateinit var songPicture: ImageView
    private lateinit var songName: TextView
    private lateinit var songArtist: TextView
    private lateinit var playButton: ImageButton
    private lateinit var rewindButton: ImageButton
    private lateinit var forwardButton: ImageButton
    private lateinit var volumeBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_song)

        backButton = findViewById(R.id.backButton)
        songPicture = findViewById(R.id.songPicture)
        songPlayer = findViewById(R.id.songPlayer)
        lifecycle.addObserver(songPlayer)
        songName = findViewById(R.id.songsName)
        songArtist = findViewById(R.id.songsArtist)
        playButton = findViewById(R.id.playButton)
        rewindButton = findViewById(R.id.prevButton)
        forwardButton = findViewById(R.id.nextButton)
        volumeBar = findViewById(R.id.volumeBar)

        val position = intent.extras?.getInt("position")

        backButton.setOnClickListener {
            val intent = Intent(this, PlaylistSongsActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(intent)
        }

        songPicture.setImageResource(R.drawable.empty_playlist)

        songName.text = intent.extras?.getString("song_name")
        songArtist.text = intent.extras?.getString("song_artist")


//        playButton.setOnClickListener {  }
//
//        rewindButton.setOnClickListener {  }
//
//        forwardButton.setOnClickListener {  }
//
//        volumeBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {
//            }
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//            }
//        })
    }
}