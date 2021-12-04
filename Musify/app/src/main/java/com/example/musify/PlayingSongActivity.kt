package com.example.musify

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import java.util.regex.Matcher
import java.util.regex.Pattern

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
//import com.google.android.youtube.player.YouTubePlayerView

// need to implement volume seekbar

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
    private lateinit var audioManager: AudioManager
    private lateinit var videoID: String

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
        videoID = getString(R.string.videoID)

        val position = intent.extras?.getInt("position")
        val song_position = intent.extras?.getInt("song_position")
        val song_url = intent.extras?.getString("song_url")
        songName.text = intent.extras?.getString("song_name")
        songArtist.text = intent.extras?.getString("song_artist")

        videoID = song_url?.let { extractYTId(it) }.toString()

        backButton.setOnClickListener {
            val intent = Intent(this, PlaylistSongsActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(intent)
        }

        songPicture.setImageResource(R.drawable.empty_playlist)




//       playButton.setOnClickListener {  }

        rewindButton.setOnClickListener {
            when {
                Repository.playList[position!!].songs.size == 1 -> { // if there's only one song, restart it
                    val intent = Intent(this, PlayingSongActivity::class.java).apply {
                        putExtra("position", position)
                        putExtra("song_position", song_position)
                        putExtra("song_name", Repository.playList[position!!].songs[song_position!!].name)
                        putExtra("song_artist", Repository.playList[position!!].songs[song_position].artist)
                    }
                    startActivity(intent)
                }
                song_position == 0 -> { // if first song, go to last in playlist
                    val prevSongPos = Repository.playList[position!!].songs.size - 1
                    val intent = Intent(this, PlayingSongActivity::class.java).apply {
                        putExtra("position", position)
                        putExtra("song_position", prevSongPos)
                        putExtra("song_name", Repository.playList[position!!].songs[prevSongPos].name)
                        putExtra("song_artist", Repository.playList[position].songs[prevSongPos].artist)
                    }
                    startActivity(intent)
                }
                else -> { // not the first song, just play prev song
                    val prevSongPos = song_position!! - 1
                    val intent = Intent(this, PlayingSongActivity::class.java).apply {
                        putExtra("position", position)
                        putExtra("song_position", prevSongPos)
                        putExtra("song_name", Repository.playList[position!!].songs[prevSongPos].name)
                        putExtra("song_artist", Repository.playList[position].songs[prevSongPos].artist)
                    }
                    startActivity(intent)
                }
            }
            }

        forwardButton.setOnClickListener {
            when {
                Repository.playList[position!!].songs.size == 1 -> { // if there's only one song, restart it
                    val intent = Intent(this, PlayingSongActivity::class.java).apply {
                        putExtra("position", position)
                        putExtra("song_position", song_position)
                        putExtra(
                            "song_name",
                            Repository.playList[position!!].songs[song_position!!].name
                        )
                        putExtra(
                            "song_artist",
                            Repository.playList[position!!].songs[song_position].artist
                        )
                    }
                    startActivity(intent)
                }
                song_position == Repository.playList[position!!].songs.size - 1 -> { // if last song go to first
                    val nextSongPos = 0
                    val intent = Intent(this, PlayingSongActivity::class.java).apply {
                        putExtra("position", position)
                        putExtra("song_position", nextSongPos)
                        putExtra(
                            "song_name",
                            Repository.playList[position!!].songs[nextSongPos].name
                        )
                        putExtra(
                            "song_artist",
                            Repository.playList[position].songs[nextSongPos].artist
                        )
                    }
                    startActivity(intent)
                }
                else -> { // else go to next song
                    val nextSongPos = song_position!! + 1
                    val intent = Intent(this, PlayingSongActivity::class.java).apply {
                        putExtra("position", position)
                        putExtra("song_position", nextSongPos)
                        putExtra(
                            "song_name",
                            Repository.playList[position!!].songs[nextSongPos].name
                        )
                        putExtra(
                            "song_artist",
                            Repository.playList[position].songs[nextSongPos].artist
                        )
                    }
                    startActivity(intent)
                }
            }
        }

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager;
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeBar.setMax(maxVolume)
        volumeBar.setProgress(curVolume)
        volumeBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1, 0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })



//        songPlayer.addYouTubePlayerListener(YouTubePlayerListener() {
//            fun onReady(youTubePlayer: YouTubePlayer) {
//                if (song_url != null) {
//                    extractYTId(song_url)?.let { youTubePlayer.loadVideo(it, 0f) }
//                }
//            }
//
//        })
    }

    fun extractYTId(url: String) : String {
        var vID = getString(R.string.videoID)
        val p : Pattern = Pattern.compile(
            "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
            Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = p.matcher(url)
        if (matcher.matches()) {
            vID = matcher.group(1)
        }

        return vID
    }
}