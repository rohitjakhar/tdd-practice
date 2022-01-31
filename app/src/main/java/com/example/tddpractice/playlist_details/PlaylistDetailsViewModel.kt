package com.example.tddpractice.playlist_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(private val playlistDetailsService: PlaylistDetailsService) :
    ViewModel() {
    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(playlistId: String) {
        viewModelScope.launch {
            playlistDetailsService.fetchPlaylistDetails(playlistId)
                .collect {
                    playlistDetails.postValue(it)
                }
        }
    }
}
