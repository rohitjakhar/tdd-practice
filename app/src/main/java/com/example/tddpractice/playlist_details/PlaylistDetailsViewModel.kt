package com.example.tddpractice.playlist_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(private val playlistDetailsService: PlaylistDetailsService) :
    ViewModel() {
    val loader = MutableLiveData<Boolean>()
    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(playlistId: String) {
        viewModelScope.launch {
            loader.postValue(true)
            playlistDetailsService.fetchPlaylistDetails(playlistId)
                .onEach {
                    loader.postValue(false)
                }
                .collect {
                    playlistDetails.postValue(it)
                }
        }
    }
}
