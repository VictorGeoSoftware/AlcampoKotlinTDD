package com.victor.test.alcampokotlin

import android.app.Application
import com.victor.test.alcampokotlin.di.*
import com.victor.test.alcampokotlin.network.bodies.GetStoreListByRegionBody
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import javax.inject.Inject

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */
class MainApplication: Application() {

    private val component: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).build() }

    var presenterComponent: PresenterComponent? = null



    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }


    // https://medium.com/@kashwin95kumar/custom-scoping-in-dagger-2-for-android-9cf6030c2f8a
    fun createPresenterComponent(): PresenterComponent {
        return component.plus(PresenterModule())
    }

    fun releasePresenterComponent() {
        presenterComponent = null
    }
}