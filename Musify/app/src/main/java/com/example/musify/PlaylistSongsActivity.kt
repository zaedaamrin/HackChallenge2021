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
    private lateinit var backButton: Button
    private lateinit var addButton: Button
    private lateinit var playlistName: TextView
    private lateinit var playlistSize: TextView
    private lateinit var playlistImage: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist_songs)

        //gets the playlist object that was opened
        val position = intent.extras?.getInt("position")
        val playlist = Repository.playList[position!!]

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }

        addButton = findViewById(R.id.addSongButton)
        addButton.setOnClickListener {
            Log.d("add song", "trying to add song")
            val intent = Intent(this, SongDetailsActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.songsRecyclerView)

        val adapter = SongAdapter(playlist.songs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        playlistName = findViewById(R.id.songsPlaylistName)
        playlistSize = findViewById(R.id.songsPlaylistSize)
        playlistImage = findViewById(R.id.songsPlaylistImage)

        playlistName.text = intent.extras?.getString("name")
        playlistSize.text = intent.extras?.getString("size")
        playlistImage.setImageResource(R.drawable.empty_playlist)

        addNewSong(adapter, position, playlist.songs)
    }

    // if coming to this activity from the "add song screen"
    private fun addNewSong(adapter: SongAdapter, position: Int, playlist: MutableList<Song>) {

        val name = intent.extras?.getString("song_name")
        val artist = intent.extras?.getString("artist")
        val songUrl = intent.extras?.getString("songUrl")

        if (name != null && artist != null && songUrl != null) {
            playlist.add(Song(name, artist, songUrl, R.drawable.empty_playlist))
        }

        adapter.notifyItemChanged(position) // pass it position of new song
    }
}