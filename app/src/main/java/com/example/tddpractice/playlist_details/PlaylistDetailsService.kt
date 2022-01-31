package com.example.tddpractice.playlist_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor(
    private val playlistDetailsAPI: PlaylistDetailsAPI
) {
    suspend fun fetchPlaylistDetails(playlistId: String): Flow<Result<PlaylistDetails>> {
        return flow {
            emit(Result.success(playlistDetailsAPI.fetchPlaylistDetails(playlistId)))
        }.catch {
            emit(Result.failure(RuntimeException("Network Error")))
        }
    }
}
