package com.example.tddpractice.playlist_details

import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistDetailsAPI {
    @GET("playlist-details/{playlistId}")
    suspend fun fetchPlaylistDetails(@Path("playlistId") playlistId: String): PlaylistDetails
}
