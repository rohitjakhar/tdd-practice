package com.example.tddpractice.playlist

import retrofit2.http.GET

interface PlaylistAPI {
    @GET("playlists")
    suspend fun fetchAllPlaylist(): List<Playlist>
}
