package com.example.tddpractice.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository(private val playlistService: PlaylistService) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> =
        playlistService.fetchPlaylists()
}
