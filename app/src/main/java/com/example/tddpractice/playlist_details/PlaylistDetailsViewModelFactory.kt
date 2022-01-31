package com.example.tddpractice.playlist_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlaylistDetailsViewModelFactory @Inject constructor(
    private val playlistDetailsService: PlaylistDetailsService
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistDetailsViewModel(playlistDetailsService) as T
    }
}
