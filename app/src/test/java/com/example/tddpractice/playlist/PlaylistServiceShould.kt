package com.example.tddpractice.playlist

import com.example.tddpractice.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.RuntimeException

class PlaylistServiceShould : BaseUnitTest() {

    private val playlistAPI: PlaylistAPI = mock()
    private val playlists: List<PlaylistRaw> = mock()

    @Test
    fun fetchPlaylistFromApi() = runBlockingTest {
        val service = PlaylistService(playlistAPI)
        service.fetchPlaylists().first()

        verify(playlistAPI, times(1)).fetchAllPlaylist()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
        val service = mockSuccessfulCase()

        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    private suspend fun mockSuccessfulCase(): PlaylistService {
        val service = mockFailureCase()
        return service
    }

    private suspend fun mockFailureCase(): PlaylistService {
        whenever(playlistAPI.fetchAllPlaylist()).thenReturn(playlists)
        val service = PlaylistService(playlistAPI)
        return service
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        whenever(playlistAPI.fetchAllPlaylist()).thenThrow(RuntimeException("Damn backend developer"))

        val service = PlaylistService(playlistAPI)

        assertEquals(
            "Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }
}
