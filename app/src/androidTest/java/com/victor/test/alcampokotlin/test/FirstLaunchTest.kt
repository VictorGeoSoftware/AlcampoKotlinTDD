package com.victor.test.alcampokotlin.test

import android.content.Intent
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.victor.test.alcampokotlin.ui.MainActivity
import com.victor.test.alcampokotlin.ui.StoreActivity
import com.victor.test.alcampokotlin.utils.mTrace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.*
import org.junit.Assert.assertNotNull
import org.junit.Rule
import kotlin.reflect.jvm.jvmName

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// TODO :: keep an eye with this, in Instrumentation class
/*
@CucumberOptions(features = ["features"], glue = ["com.victor.test.alcampokotlin.test"])
If I want to keep my Test classes in victor.test.alcampokotlin, I have to change glue content -> glue = ["com.victor.test.alcampokotlin"])
 */

class FirstLaunchTest {
    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var mDevice: UiDevice
    private lateinit var mainActivity: MainActivity


    @Before
    fun setUp() {
        mTrace("setUp!")
        Intents.init()
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @After
    fun tearDown() {
        mTrace("tearDown!")
        Intents.release()
        mainActivityTestRule.finishActivity()
    }


    // TODO :: terminar de testear bien
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- TEST CASES -----------------------------------------
    @Given("^a user launching the app for first time")
    fun a_user_launching_the_app_for_first_time() {
        assertNotNull(mainActivity)
        onView(withId(R.id.txt_store_name)).check(matches(withText("")))
    }

    @When("^no favourite store is received")
    fun no_favourite_store_is_received() {
        onView(allOf(withId(android.support.design.R.id.snackbar_action))).perform(click())
        intended(hasComponent(StoreActivity::class.jvmName))
    }

    @And("^the store view is launched")
    fun the_store_view_is_launched() {
//        onView(withId(R.id.txt_stores_title)).check(matches(isDisplayed()))
    }

    @And("^app have not location permissions")
    fun app_have_not_location_permissions() {
        onView(withId(R.id.layout_no_stores)).check(matches(isDisplayed()))
        onView(withId(R.id.lst_stores)).check(matches(not(isDisplayed())))
    }

    @And("^user tap on grant location button")
    fun user_tap_on_grant_location_button() {
        onView(withId(R.id.btn_grant_location_permission)).perform(click())
    }

    @And("^user grant location permission")
    fun user_grant_location_permission() {
        allowLocationPermissions()
    }

    @Then("^store list is retrieved and shown")
    fun store_list_is_retrieved_and_shown() {
        Thread.sleep(3000)
        onView(withId(R.id.layout_no_stores)).check(matches(not(isDisplayed())))
        onView(withId(R.id.lst_stores)).check(withItemCount(greaterThan(0)))

        // TODO :: complete test choosing one, notifying to server, and coming back to MainActivity
    }




    // Interesting for test location permissions
    // https://caster.io/lessons/using-the-grant-permission-rule-in-espresso
    // https://medium.com/exploring-android/handling-android-runtime-permissions-in-ui-tests-981f9dc11a4e -> deprecated I guess, but it going to be made ..
    // http://www.vogella.com/tutorials/AndroidTestingUIAutomator/article.html

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- METHODS --------------------------------------------
    private fun allowLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            val btnAllow = mDevice.findObject(UiSelector().text("ALLOW"))

            if (btnAllow.exists()) {
                try {
                    btnAllow.click()
                } catch (e: Exception) {
                    mTrace(e.localizedMessage)
                }
            }
        }
    }
}
