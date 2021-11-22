package com.example.musify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val addButton: Button
    private lateinit var recyclerView: recyclerView
    private val playList = mutableListOf<Playlist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, PlayListDetailsActivity::class.java).apply {
                putExtra("name", "")
            }
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}