package com.example.tddpractice

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.tddpractice.playlist.client
import com.example.tddpractice.playlist.idlingResource
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class PlayListFeature : BaseUItest() {

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displaysListOfPlaylist() {
        assertRecyclerViewItemCount(R.id.playlists_list, 10)

        onView(
            allOf(
                withId(R.id.playlist_name),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))
            )
        )
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_category),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))
            )
        )
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 1))
            )
        )
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayLoaderWhileFetchingTHePlaylists() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoaderAfterFetchingPlaylists() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displayRockIMageForRockListItems() {
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 3))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailsScreen() {
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))
            )
        )
            .perform(click())

        assertDisplayed(R.id.playlists_details_root)
    }
}
