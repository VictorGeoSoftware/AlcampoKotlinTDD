package com.victor.test.alcampokotlin.utils

import android.app.Activity
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victor.test.alcampokotlin.MainApplication

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */
fun Any.traceObject(someObjects: Any) {
    System.out.println("AlcampoKotlin || ${this.javaClass} - $someObjects")
}

val Activity.app: MainApplication
    get() = application as MainApplication

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)