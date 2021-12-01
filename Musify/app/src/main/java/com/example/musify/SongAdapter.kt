package com.example.musify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val songs: List<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName : TextView = itemView.findViewById(R.id.songName)
        var artistName : TextView = itemView.findViewById(R.id.artistName)
        var songImage : ImageView = itemView.findViewById(R.id.songImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_cell, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.songName.text = song.name
        holder.artistName.text = song.artist
        holder.songImage.setImageDrawable(song.image)
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}