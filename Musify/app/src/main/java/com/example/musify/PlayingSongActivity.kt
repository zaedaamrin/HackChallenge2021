package com.example.musify

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Toast
import android.widget.*
//import com.google.android.youtube.player.YouTubeBaseActivity
//import com.google.android.youtube.player.YouTubeInitializationResult
//import com.google.android.youtube.player.YouTubePlayer
//import com.google.android.youtube.player.YouTubePlayerView


class PlayingSongActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
//    private lateinit var ytPlayer: YouTubePlayerView
    private lateinit var songPicture: ImageView
    private lateinit var songName: TextView
    private lateinit var songArtist: TextView
    private lateinit var playButton: ImageButton
    private lateinit var rewindButton: ImageButton
    private lateinit var forwardButton: ImageButton
    private lateinit var volumeBar: SeekBar
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_song)

        backButton = findViewById(R.id.backButton)
        songPicture = findViewById(R.id.songPicture)
//        ytPlayer = findViewById(R.id.songPlayer)
        songName = findViewById(R.id.songsName)
        songArtist = findViewById(R.id.songsArtist)
        playButton = findViewById(R.id.playButton)
        rewindButton = findViewById(R.id.prevButton)
        forwardButton = findViewById(R.id.nextButton)
        volumeBar = findViewById(R.id.volumeBar)

        val position = intent.extras?.getInt("position")
        val song_position = intent.extras?.getInt("song_position")
        val song_url = intent.extras?.getString("song_url")
        songName.text = intent.extras?.getString("song_name")
        songArtist.text = intent.extras?.getString("song_artist")

        backButton.setOnClickListener {
            val intent = Intent(this, PlaylistSongsActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(intent)
        }

        songPicture.setImageResource(R.drawable.empty_playlist)

        val api_key = "AIzaSyDud1K_9jWyGlOd-H4V96qAjDiYnrxCm58"

//        ytPlayer.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                provider: YouTubePlayer.Provider?,
//                player: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                player?.loadVideo("HzeK7g8cD0Y")
//                player?.play()
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(this@PlayingSongActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
//            }
//        })



        // implement the play button after implementing the video
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
    }
}