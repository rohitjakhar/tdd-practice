package com.example.tddpractice.playlist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistService: PlaylistService
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> =
        playlistService.fetchPlaylists()
}
