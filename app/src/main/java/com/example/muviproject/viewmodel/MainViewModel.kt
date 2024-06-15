package com.example.muviproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muviproject.R

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    /**
     * Display a toast message when the notification icon was clicked.
     */
    fun onNotificationIconClicked() {
        _toastMessage.value = getApplication<Application>().getString(R.string.coming_soon)
    }

    /**
     * Display a toast message when the tv icon was clicked.
     */
    fun onTvIconClicked() {
        _toastMessage.value = getApplication<Application>().getString(R.string.coming_soon)
    }
}