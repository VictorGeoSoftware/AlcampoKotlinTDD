package com.victor.test.alcampokotlin

import android.app.Application
import com.victor.test.alcampokotlin.di.AppComponent
import com.victor.test.alcampokotlin.di.AppModule
import com.victor.test.alcampokotlin.di.DaggerAppComponent

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */
class MainApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}