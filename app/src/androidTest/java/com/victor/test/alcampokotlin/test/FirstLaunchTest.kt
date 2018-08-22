package com.victor.test.alcampokotlin.test

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.ui.MainActivity
import com.victor.test.alcampokotlin.ui.StoreActivity
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import kotlin.reflect.jvm.jvmName

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@RunWith(AndroidJUnit4::class)
class FirstLaunchTest {
    @Rule val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Rule val storeActivityTestRule: IntentsTestRule<StoreActivity> = IntentsTestRule(StoreActivity::class.java)
    private lateinit var activity: Activity
    private var storeActivity: StoreActivity? = null


    @Before
    fun setUp() {
        Intents.init()
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After
    fun tearDown() {
        Intents.release()
        activityTestRule.finishActivity()
    }



    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- TEST CASES -----------------------------------------
    @Given("^A user is in MainActivity")
    fun A_user_is_in_MainActivity() {
        // TODO :: activity is null, review!
        assertNotNull(activity)
    }

    @When("^No favourite store is defined")
    fun No_favourite_store_is_defined() {
        onView(withId(R.id.txt_no_items)).check(matches(isDisplayed()))
    }

    @Then("^I should open StoreActivity")
    fun I_should_open_StoreActivity() {
//        intended(hasComponent(ComponentName(getTargetContext(), StoreActivity::class.java)))
        intended(hasComponent(StoreActivity::class.jvmName))
        onView(withId(R.id.layout_no_stores)).check(matches(isDisplayed()))

//        storeActivity = getCurrentActivity() as StoreActivity
//        storeActivity?.finish()
    }

    // TODO :: keep an eye with this, in Instrumentation class
    /*
    @CucumberOptions(features = ["features"], glue = ["com.victor.test.alcampokotlin.test"])
    If I want to keep my Test classes in victor.test.alcampokotlin, I have to change glue content -> glue = ["com.victor.test.alcampokotlin"])
     */


    // Interesting for test location permissions
    // https://medium.com/exploring-android/handling-android-runtime-permissions-in-ui-tests-981f9dc11a4e
}
