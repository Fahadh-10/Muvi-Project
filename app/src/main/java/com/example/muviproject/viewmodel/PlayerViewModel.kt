package com.example.muviproject.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val _player = MutableLiveData<ExoPlayer?>()
    val player: LiveData<ExoPlayer?> get() = _player
    private var isPlayerInitialized = false
    private var playbackPosition: Long = 0
    private var currentMediaItemIndex: Int = 0
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    /**
     * Initializes the ExoPlayer with the provided video URL.
     * @param videoUrl The URL of the video to be played.
     */
    fun initializePlayer(videoUrl: String) {
        viewModelScope.launch {
            try {
                if (!isPlayerInitialized && videoUrl.isNotEmpty()) {
                    val player = ExoPlayer.Builder(getApplication()).build()
                    val mediaItem = withContext(Dispatchers.IO) {
                        MediaItem.fromUri(videoUrl)
                    }
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.seekTo(currentMediaItemIndex, playbackPosition)
                    _player.value = player
                    isPlayerInitialized = true
                } else {
                    _player.value?.seekTo(currentMediaItemIndex, playbackPosition)
                    _player.value?.playWhenReady = true
                }
            } catch (e: Exception) {
                Log.e("Player", "Error: ${e.message}")
            }
        }
    }

    /**
     * Starts the playback of the current ExoPlayer instance.
     */
    fun startPlayer() {
        _player.value?.playWhenReady = true
    }

    /**
     * Pauses the playback of the current ExoPlayer instance.
     */
    fun stopPlayer() {
        _player.value?.playWhenReady = false
    }

    /**
     * Saves the current playback state, including the playback position and media item index.
     */
    fun savePlayerState() {
        _player.value?.let {
            playbackPosition = it.currentPosition
            currentMediaItemIndex = it.currentMediaItemIndex
        }
    }

    /**
     * Releases the ExoPlayer resources by saving the current playback state, releasing the player,
     * and setting the player instance to null. Resets the player initialization flag.
     */
    fun releasePlayer() {
        savePlayerState()
        _player.value?.release()
        _player.value = null
        isPlayerInitialized = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}


