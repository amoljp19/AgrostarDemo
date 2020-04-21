package com.softaai.agrostardemoassignment.view

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf

val isDisplayed: ViewAssertion = matches(ViewMatchers.isDisplayed())

infix fun ViewInteraction.check(action: ViewAssertion): ViewInteraction = this.check(action)

infix fun ViewInteraction.hasHint(@StringRes string: Int): ViewAssertion = matches(withHint(string))

infix fun ViewInteraction.hasText(@StringRes string: Int): ViewAssertion = matches(withText(string))

infix fun Int.perform(action: ViewAction): ViewInteraction = onView(withId(this)).perform(action)

infix fun Int.check(action: ViewAssertion): ViewInteraction = onView(withId(this)).check(action)

infix fun Int.hasHint(@StringRes resource: Int): ViewAssertion =
    onView(withId(this)) hasHint resource

infix fun Int.hasText(@StringRes resource: Int): ViewAssertion =
    onView(withId(this)) hasText resource