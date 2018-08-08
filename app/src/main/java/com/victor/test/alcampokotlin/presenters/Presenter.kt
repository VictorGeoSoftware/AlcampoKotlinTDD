package com.victor.test.alcampokotlin.presenters

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
abstract class Presenter<T1> {
    var view: T1? = null





    open fun destroy() {
        System.out.println("pasa por destroy!")
    }
}