package com.example.tddpractice

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.tddpractice.playlist.idlingResource
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlaylistDetailsFeature : BaseUItest() {

    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToPlaylistDetails()

        assertDisplayed("Hard Rock Cafe")

        assertDisplayed("Rock your sense with this timeless signature vives list. \n\n 1. Poison \n 2. You shook me all night \n 3. Zombie \n 4. Rock'n Me \n 5. Thunderstuck \n 6. I hate my self for loving you. \n 7. Crazy")
    }

    @Test
    fun displayLoaderWhileLoading() {
        navigateToPlaylistDetails()
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(
            R.id.loader_playlist_details
        )
    }

    private fun navigateToPlaylistDetails() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.playlists_list),
                        0
                    )
                )
            )
        )
            .perform(ViewActions.click())
    }
}
