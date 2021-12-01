package com.example.musify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var addPlaylistButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPlaylistButton = findViewById(R.id.addButton)
        addPlaylistButton.setOnClickListener { //if (playlistsList.size == 0 ) {
            Log.d("recyclerview size", Repository.playList.size.toString())
            val intent = Intent(this, PlaylistDetailsActivity::class.java).apply {
                putExtra("position", Repository.playList.size) // checked position is correct
            }
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        val adapter = PlaylistAdapter(Repository.playList)
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val name = intent.extras?.getString("name")
        val position = intent.extras?.getInt("position")

        if (position != null && name != null) {
//            if (position == 0 || position <= playlistsList.size) {  // if coming back to home activity from plus button/creating new playlist
            val new = Playlist(name, 0, R.drawable.empty_playlist)
            Repository.playList.add(new)
            adapter.notifyItemChanged(position)
//            }
//            else { // if coming back to home activity from a playlist
//                playlistsList[position] = Playlist(name, 0, R.drawable.empty_playlist)
//                adapter.notifyItemChanged(position)
//            }
        }
    }
}