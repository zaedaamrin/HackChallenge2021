package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlaylistSongsActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var addButton: Button
    private lateinit var playButton: Button
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
        Log.d("playlist_songs", ("opening playlist activity: " + playlist.toString()))

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }

        // button opens song input screen
        addButton = findViewById(R.id.addSongButton)
        addButton.setOnClickListener {
            val intent = Intent(this, SongDetailsActivity::class.java)
                .putExtra("playlistPosition", position)
            startActivity(intent)
        }

        playButton = findViewById(R.id.playButton)

        recyclerView = findViewById(R.id.songsRecyclerView)

        val adapter = SongAdapter(playlist.songs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        playlistName = findViewById(R.id.songsPlaylistName)
        playlistSize = findViewById(R.id.songsPlaylistSize)
        playlistImage = findViewById(R.id.songsPlaylistImage)

        playlistName.text = intent.extras?.getString("name")
        val numSongs = intent.extras?.getInt("size")
        if (numSongs!! == 1) { playlistSize.text = getString(R.string.size_one) }
        else { playlistSize.text = ("$numSongs Songs") }
        playlistImage.setImageResource(R.drawable.empty_playlist)


        addNewSong(adapter, position, playlist.songs)
    }

    // adds a song to songs list in playlist object from repository if user input new song
    private fun addNewSong(adapter: SongAdapter, position: Int, playlist: MutableList<Song>) {
        val name = intent.extras?.getString("songName")
        val artist = intent.extras?.getString("artist")
        val songUrl = intent.extras?.getString("url")

        if (name != null && artist != null && songUrl != null) {
            Log.d("playlist_songs", playlist.toString())
            playlist.add(Song(name, artist, songUrl, R.drawable.empty_playlist))
            Log.d("playlist_songs", ("after adding song: $playlist"))
        }

        adapter.notifyItemChanged(position) // pass it position of new song
    }
}