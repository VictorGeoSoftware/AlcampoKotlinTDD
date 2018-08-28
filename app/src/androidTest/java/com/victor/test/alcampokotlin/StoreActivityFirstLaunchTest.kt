package com.victor.test.alcampokotlin

import android.content.Intent
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.support.v7.widget.RecyclerView
import android.view.View
import com.victor.test.alcampokotlin.StoreActivityFirstLaunchTest.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.victor.test.alcampokotlin.ui.StoreActivity
import com.victor.test.alcampokotlin.utils.mTrace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert.assertNotNull
import org.junit.Rule

/**
 * Created by victorpalmacarrasco on 27/8/18.
 * ${APP_NAME}
 */
class StoreActivityFirstLaunchTest {
    @Rule
    val storeActivityTestRule: ActivityTestRule<StoreActivity> = ActivityTestRule(StoreActivity::class.java)

    private lateinit var mDevice: UiDevice
    private lateinit var storeActivity: StoreActivity


    @Before
    fun setUp() {
        Intents.init()
        storeActivityTestRule.launchActivity(Intent())
        storeActivity = storeActivityTestRule.activity

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @After
    fun tearDown() {
        Intents.release()
        storeActivityTestRule.finishActivity()
    }


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- TEST CASES -----------------------------------------
    @Given("^the store view launched")
    fun the_store_view_launched() {
        assertNotNull(storeActivity)
    }

    @When("^app have not location permissions")
    fun app_have_not_location_permissions() {
        mTrace("app_have_not_location_permissions!")
        onView(withId(R.id.layout_no_stores)).check(matches(isDisplayed()))
        onView(withId(R.id.lst_stores)).check(matches(not(isDisplayed())))
    }

    @And("^user tap on grant location button")
    fun user_tap_on_grant_location_button() {
        mTrace("user_tap_on_grant_location_button!")
        onView(withId(R.id.btn_grant_location_permission)).perform(click())
    }

    @And("^user grant location permission")
    fun user_grant_location_permission() {
        mTrace("user_grant_location_permission!")
        allowLocationPermissions()
    }

    @Then("^store list is retrieved and shown")
    fun store_list_is_retrieved_and_shown() {
        // https://developer.android.com/training/testing/espresso/lists

        // onView(withId(R.id.lstWeatherForecast)).check(withItemCount(greaterThan(5)))
        mTrace("store_list_is_retrieved_and_shown!")
        onView(withId(R.id.txt_stores_title)).check(matches(withText("Stores in 14 cities")))
        onView(withId(R.id.lst_stores)).check(withItemCount(greaterThan(0)))
    }


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- METHODS --------------------------------------------
    private fun allowLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            val btnAllow = mDevice.findObject(UiSelector().text("Allow"))

            if (btnAllow.exists()) {
                try {
                    btnAllow.click()
                } catch (e: Exception) {
                    mTrace(e.localizedMessage)
                }
            }
        }
    }

    private class RecyclerViewItemCountAssertion: ViewAssertion {
        lateinit var matcher: Matcher<Int>


        constructor(matcher: Matcher<Int>) {
            this.matcher = matcher
        }


        companion object {
            fun withMyItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
                return withItemCount(CoreMatchers.`is`(expectedCount))
            }

            fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
                return RecyclerViewItemCountAssertion(matcher)
            }
        }



        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter.itemCount, matcher)
        }

    }
}