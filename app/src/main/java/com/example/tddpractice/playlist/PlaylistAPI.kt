package com.example.tddpractice.playlist

interface PlaylistAPI {
    suspend fun fetchAllPlaylist(): List<Playlist> {
        return listOf()
    }
}
