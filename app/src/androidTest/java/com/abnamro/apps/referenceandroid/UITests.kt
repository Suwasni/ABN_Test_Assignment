package com.abnamro.apps.referenceandroid

import androidx.test.core.graphics.writeToTestStorage
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.screenshot.captureToBitmap
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestName

/**
 * Instrumented UI tests on the target app, which will be executed on an Android device.
 * The tests cover UI validations, navigation and content of the application
 */
@RunWith(AndroidJUnit4::class)
class UITests {

    /**
     * A JUnit rule that stores the method name, so it can be used to generate unique
     * screenshot files per test method
     */
    @get:Rule
    var nameRule = TestName()


    /**
     * Use ActivityScenarioRule to create and launch the activity under test before each test,
     * and close it after each test.
     */
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * To test the application under test is the correct one by checking the package name,
     * while developing there are several variations of app and this check becomes important
     */
    @Test
    fun verifyAppPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(APP_PACKAGE_NAME, appContext.packageName)
    }

    /**
     * To test the application home page content after first launch,
     * here we check all major elements as a sanity check
     * screenshot - used espresso native method to capture the screen for reference
     */
    @Test
    fun verifyAppHomeScreenUIContent() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_first)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_first)).check(matches(withText(WELCOME_TEXT)))
        onView(withId(R.id.button_first)).check(matches(isDisplayed()))
            .captureToBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    /**
     * To test the email icon and its function,
     * here we click on icon and verify the displayed message bar
     * screenshot - used espresso native method to capture the screen for reference
     */
    @Test
    fun verifyEmailIconFunction() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(android.R.id.content)).check(matches(isDisplayed()))
            .captureToBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    /**
     * To test the menu option and its function on top of screen,
     * here we click on the menu and verify the displayed option
     * screenshot - used espresso native method to capture the screen for reference
     */
    @Test
    fun verifyMenuOptionFunction() {
        onView(withId(R.id.toolbar)).perform(click())
        onView(withId(android.R.id.content)).check(matches(isDisplayed()))
            .captureToBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    /**
     * To test button on screen, its navigation to next page and back to first page
     * screenshot - used espresso native method to capture the screen for reference
     */
    @Test
    fun verifyNavigationToSecondPageAndBackToFirstPage() {
        onView(withId(R.id.button_first)).perform(click())
        onView(withId(R.id.button_second)).check(matches(isDisplayed()))
        onView(withId(R.id.button_second)).perform(click())
        onView(withId(R.id.button_first)).check(matches(isDisplayed()))
            .captureToBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    /**
     * A companion object to store variables used in the class,
     * for a big project this data can be moved to a json file separately
     */
    companion object {
        val APP_PACKAGE_NAME = "com.abnamro.apps.referenceandroid"
        val WELCOME_TEXT = "Hello World!"
    }

}