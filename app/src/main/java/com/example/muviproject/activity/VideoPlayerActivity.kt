package com.example.muviproject.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.example.muviproject.R
import com.example.muviproject.databinding.ActivityVideoPlayerBinding
import com.example.muviproject.helper.NavKey
import com.example.muviproject.viewmodel.PlayerViewModel

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var videoUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readBundles()
        setupObservers()
        viewModel.initializePlayer(videoUrl)
    }

    /**
     * Observes changes in the player instance from the view model and updates the player view accordingly.
     */
    private fun setupObservers() {
        viewModel.player.observe(this, Observer { player ->
            binding.playerView.player = player
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.startPlayer()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopPlayer()
        viewModel.savePlayerState()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.releasePlayer()
    }

    /**
     * Reads the video URL passed through intent and initializes the `videoUrl` property.
     */
    private fun readBundles() {
        if (intent.hasExtra(NavKey.VIDEO_URL)) {
            videoUrl = intent.getStringExtra(NavKey.VIDEO_URL).toString()
        }
    }
}
