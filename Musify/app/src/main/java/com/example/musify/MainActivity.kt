package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var addPlaylistButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val playlistsList = mutableListOf<Playlist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPlaylistButton = findViewById(R.id.addButton)
        addPlaylistButton.setOnClickListener { //if (playlistsList.size == 0 ) {
            val intent = Intent(this, PlaylistDetailsActivity::class.java).apply {
                putExtra("position", playlistsList.size)
            }
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        val adapter = PlaylistAdapter(playlistsList)
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val name = intent.extras?.getString("name")
        val position = intent.extras?.getInt("position")

        if (position != null && name != null) {
            if (position == 0 || position >= playlistsList.size) {
                playlistsList.add(Playlist(name, 0, R.drawable.empty_playlist))
                adapter.notifyItemChanged(position)
            }
            else {
                playlistsList[position] = Playlist(name, 0, R.drawable.empty_playlist)
                adapter.notifyItemChanged(position)
            }
        }
    }
}