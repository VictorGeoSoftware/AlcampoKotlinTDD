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


    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
    lateinit var presenterComponent: PresenterComponent



    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        presenterComponent = component.plus(PresenterModule())
    }
}