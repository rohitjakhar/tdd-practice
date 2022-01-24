package com.example.tddpractice.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistService: PlaylistService,
    private val mapper: PlaylistMapper
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> =
        playlistService.fetchPlaylists().map {
            if (it.isSuccess) {
                Result.success(mapper(it.getOrNull()!!))
            } else
                Result.failure(it.exceptionOrNull()!!)
        }
}
