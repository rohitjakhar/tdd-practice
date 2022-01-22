package com.example.tddpractice.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    val playlist = liveData<Result<List<Playlist>>> {
        loader.postValue(true)
        emitSource(
            repository.getPlaylists().onEach {
                loader.postValue(false)
            }.asLiveData()
        )
    }
}
