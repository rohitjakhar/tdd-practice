package com.example.tddpractice.playlist

import androidx.lifecycle.*

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    val playlist = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlaylists().asLiveData())
    }
}
