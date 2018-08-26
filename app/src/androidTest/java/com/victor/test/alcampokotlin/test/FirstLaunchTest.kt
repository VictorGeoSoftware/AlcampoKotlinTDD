package com.victor.test.alcampokotlin.test

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.isInternal
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.ui.StoreActivity
import com.victor.test.alcampokotlin.utils.mTrace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertNotNull
import org.junit.Rule

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
//    @Rule
//    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

//    @get:Rule
//    val storeActivityTestRule: IntentsTestRule<StoreActivity> = IntentsTestRule(StoreActivity::class.java)
    @Rule
    val storeActivityTestRule: ActivityTestRule<StoreActivity> = ActivityTestRule(StoreActivity::class.java)

    @Rule
    val grantLocationPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    private lateinit var mDevice: UiDevice


//    private lateinit var activity: Activity
    private lateinit var storeActivity: StoreActivity


    @Before
    fun setUp() {
        Intents.init()
//        activityTestRule.launchActivity(Intent())
//        activity = activityTestRule.activity
        storeActivityTestRule.launchActivity(Intent())
        storeActivity = storeActivityTestRule.activity
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        intending(not(isInternal())).respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }

    @After
    fun tearDown() {
        Intents.release()
//        activityTestRule.finishActivity()
        storeActivityTestRule.finishActivity()
    }



    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- TEST CASES -----------------------------------------
    @Given("^A user is in MainActivity")
    fun A_user_is_in_MainActivity() {
        // TODO :: activity is null, review!
        mTrace("A_user_is_in_MainActivity: $storeActivity")
        assertNotNull(storeActivity)
    }

    @When("^No favourite store is defined")
    fun No_favourite_store_is_defined() {
        mTrace("No_favourite_store_is_defined!")
//        onView(withId(R.id.txt_store_name)).check(matches(withText("")))
//        intended(hasComponent(StoreActivity::class.jvmName))

        onView(withId(R.id.layout_no_stores)).check(matches(isDisplayed()))
    }

    @Then("^I should open StoreActivity")
    fun I_should_open_StoreActivity() {
        mTrace("I_should_open_StoreActivity!")
//        onView(withId(R.id.layout_no_stores)).check(matches(isDisplayed()))
        mTrace("I_should_open_StoreActivity! -1")
//        grantLocationPermissionRule
        allowLocationPermissions()    }




    // Interesting for test location permissions
    // https://caster.io/lessons/using-the-grant-permission-rule-in-espresso
    // https://medium.com/exploring-android/handling-android-runtime-permissions-in-ui-tests-981f9dc11a4e -> deprecated I guess, but it going to be made ..
    // http://www.vogella.com/tutorials/AndroidTestingUIAutomator/article.html

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- METHODS --------------------------------------------
    private fun allowLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            val btnAllow = mDevice.findObject(UiSelector().text("Allow"))

            if (btnAllow.exists()) {
                try {
                    btnAllow.click()
                } catch (e: UiObjectNotFoundException) {
                    mTrace(e.localizedMessage)
                }
            }
        }
    }
}
