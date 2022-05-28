package com.github.johnnysc.tictactoetdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Asatryan on 28.05.2022
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_player_one_win() {
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 1, go!")))
        onView(withId(R.id.ulButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.umButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.urButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.mlButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.mrButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.mmButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.dlButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.dmButton)).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.drButton)).check(matches(DrawableMatcher(-1)))

        onView(withId(R.id.ulButton)).perform(click()).check(matches(DrawableMatcher(R.drawable.x)))
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 2, go!")))

        onView(withId(R.id.umButton)).perform(click()).check(matches(DrawableMatcher(R.drawable.o)))
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 1, go!")))

        onView(withId(R.id.mmButton)).perform(click()).check(matches(DrawableMatcher(R.drawable.x)))
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 2, go!")))

        onView(withId(R.id.mrButton)).perform(click()).check(matches(DrawableMatcher(R.drawable.o)))
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 1, go!")))

        onView(withId(R.id.drButton)).perform(click()).check(matches(DrawableMatcher(R.drawable.x)))
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 1 won!")))

        onView(withId(R.id.dlButton)).perform(click()).check(matches(DrawableMatcher(-1)))
        onView(withId(R.id.resultTextView)).check(matches(withText("Player 1 won!")))
    }

    @Test
    fun test_game_over() {
        //todo
    }
}