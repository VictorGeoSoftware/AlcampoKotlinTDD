package com.victor.test.alcampokotlin.presenters

import com.victor.test.alcampokotlin.di.DaggerPresenterComponent
import com.victor.test.alcampokotlin.di.PresenterComponent
import com.victor.test.alcampokotlin.di.PresenterModule

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
abstract class Presenter<T1> {
    var view: T1? = null
    val presenterComponent: PresenterComponent by lazy {
        DaggerPresenterComponent.builder().presenterModule(PresenterModule()).build()
    }

    open fun initialize() {}
    open fun resume() {}
    open fun pause() {}
    open fun destroy() {}
}