package com.example.tddpractice.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlaylistService(
    private val playlistAPI: PlaylistAPI
) {
    suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(playlistAPI.fetchAllPlaylist()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}
