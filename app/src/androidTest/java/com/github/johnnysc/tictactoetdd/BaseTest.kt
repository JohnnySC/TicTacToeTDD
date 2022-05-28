package com.github.johnnysc.tictactoetdd

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers

/**
 * @author Asatryan on 28.05.2022
 */
abstract class BaseTest {

    protected fun view(@IdRes id: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(id))

    protected fun ViewInteraction.checkText(text:String){
        this.check(matches(ViewMatchers.withText(text)))
    }

    protected fun ViewInteraction.checkNoDrawable() {
        this.check(matches(DrawableMatcher(-1)))
    }

    protected fun ViewInteraction.checkDrawable(@DrawableRes id:Int) {
        this.check(matches(DrawableMatcher(id)))
    }

    protected fun ViewInteraction.tap(): ViewInteraction = this.perform(ViewActions.click())
}