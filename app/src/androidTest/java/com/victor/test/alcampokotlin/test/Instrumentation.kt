package com.victor.test.alcampokotlin.test

import android.os.Bundle
import android.support.test.runner.MonitoringInstrumentation
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore

/**
 * Created by victorpalmacarrasco on 22/8/18.
 * ${APP_NAME}
 */

@CucumberOptions(features = ["features"], glue = ["com.victor.test.alcampokotlin.test"])
class Instrumentation: MonitoringInstrumentation() {
    private val instrumentationCore = CucumberInstrumentationCore(this)


    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        instrumentationCore.create(arguments)
        start()
    }

    override fun onStart() {
        super.onStart()
        waitForIdleSync()
        instrumentationCore.start()
    }
}