package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlaylistSongsActivity : AppCompatActivity() {
    private val songName = "Stay"
    private val artistName = "The Kid LAROI"
    private lateinit var backButton: Button
    private lateinit var playlistName: TextView
    private lateinit var playlistSize: TextView
    private lateinit var playlistImage: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val songList = mutableListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist_songs)

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }
        recyclerView = findViewById(R.id.songsRecyclerView)

        songList.add(Song(songName, artistName, resources.getDrawable(R.drawable.empty_playlist)))

        val adapter = SongAdapter(songList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        playlistName = findViewById(R.id.songsPlaylistName)
        playlistSize = findViewById(R.id.songsPlaylistSize)
        playlistImage = findViewById(R.id.songsPlaylistImage)

        playlistName.text = intent.extras?.getString("name")
        playlistSize.text = intent.extras?.getString("size")
        playlistImage.setImageDrawable(resources.getDrawable(R.drawable.empty_playlist))

    }
}