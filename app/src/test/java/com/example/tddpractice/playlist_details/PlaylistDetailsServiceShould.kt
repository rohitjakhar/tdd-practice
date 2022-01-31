package com.example.tddpractice.playlist_details

import com.example.tddpractice.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistDetailsServiceShould : BaseUnitTest() {
    lateinit var service: PlaylistDetailsService
    private val api = mock<PlaylistDetailsAPI>()
    private val playlistDetails: PlaylistDetails = mock()
    private val id = "1"
    private val exception = RuntimeException("Network Error")

    @Test
    fun fetchPlaylistDetailsFromAPI() = runBlockingTest {
        service = PlaylistDetailsService(api)
        service.fetchPlaylistDetails(id).single()

        verify(api, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmit() = runBlockingTest {
        emitSuccessfulCase()

        assertEquals(
            Result.success(playlistDetails),
            service.fetchPlaylistDetails(playlistId = id).first()
        )
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailCase()
        assertEquals("Network Error", service.fetchPlaylistDetails(id).single().exceptionOrNull()?.message)
    }

    private suspend fun mockFailCase() {
        service = PlaylistDetailsService(api)
        whenever(api.fetchPlaylistDetails(id)).thenThrow(
            exception
        )
    }

    private suspend fun emitSuccessfulCase() {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(
            playlistDetails
        )
        service = PlaylistDetailsService(api)
    }
}
