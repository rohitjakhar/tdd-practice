package com.example.tddpractice.playlist_details

import com.example.tddpractice.utils.BaseUnitTest
import com.example.tddpractice.utils.captureValues
import com.example.tddpractice.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class PlaylistDetailsViewModelShould : BaseUnitTest() {

    private val service = mock<PlaylistDetailsService>()
    lateinit var viewModel: PlaylistDetailsViewModel
    private val id = "1"
    private val playlistDetails: PlaylistDetails = mock()
    private val expected = Result.success(playlistDetails)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessfulCase()

        viewModel.getPlaylistDetails(id)
        verify(service, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun emitPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessfulCase()

        viewModel.getPlaylistDetails(id)
        assertEquals(expected, viewModel.playlistDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() = runBlockingTest {
        mockErrorCase()

        assertEquals(
            exception,
            viewModel.playlistDetails.getValueForTest()!!.exceptionOrNull()
        )
    }

    @Test
    fun showSpinnerWhileLoading() = runBlockingTest {
        mockSuccessfulCase()


        viewModel.loader.captureValues {
            viewModel.getPlaylistDetails(id)
            viewModel.playlistDetails.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlaylistDetailsLoad() = runBlockingTest {
        mockSuccessfulCase()

        viewModel.getPlaylistDetails(id)
        viewModel.loader.captureValues {
            viewModel.playlistDetails.getValueForTest()

            assertEquals(false, values.last())
        }
    }
    private suspend fun mockErrorCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        viewModel = PlaylistDetailsViewModel(service)
        viewModel.getPlaylistDetails(id)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = PlaylistDetailsViewModel(service)
    }
}
