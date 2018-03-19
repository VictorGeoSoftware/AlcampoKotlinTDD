package com.victor.test.alcampokotlin.utils

import android.app.Activity
import android.app.Application
import com.victor.test.alcampokotlin.MainApplication

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */
fun Activity.traceObject(someObjects: Any) {
    System.out.println("AlcampoKotlin || $this - $someObjects")
}

val Activity.app: MainApplication
    get() = application as MainApplication