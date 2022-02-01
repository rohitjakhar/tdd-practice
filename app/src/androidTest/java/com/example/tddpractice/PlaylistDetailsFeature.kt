package com.example.tddpractice

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.tddpractice.playlist.idlingResource
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlaylistDetailsFeature : BaseUItest() {

    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToPlaylistDetails(0)

        assertDisplayed("Hard Rock Cafe")

        assertDisplayed("Rock your sense with this timeless signature vives list. \n\n 1. Poison \n 2. You shook me all night \n 3. Zombie \n 4. Rock'n Me \n 5. Thunderstuck \n 6. I hate my self for loving you. \n 7. Crazy")
    }

    @Test
    fun displayLoaderWhileLoading() {
        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(2000)
        navigateToPlaylistDetails(0)

        assertDisplayed(
            R.id.loader_playlist_details
        )
    }

    @Test
    fun displaysErrorMessageWhenNetworkFails() {
        navigateToPlaylistDetails(1)
        assertDisplayed(R.string.generic_error)
    }

    @Test
    fun hideErrorMessage() {
        navigateToPlaylistDetails(1)
        Thread.sleep(3000)
        assertNotExist(R.string.generic_error)
    }
    @Test
    fun hideLoader() {
        navigateToPlaylistDetails(0)

        assertNotDisplayed(R.id.loader_playlist_details)
    }

    private fun navigateToPlaylistDetails(row: Int) {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.playlists_list),
                        row
                    )
                )
            )
        )
            .perform(ViewActions.click())
    }
}
