package com.victor.test.alcampokotlin.di

import android.app.Application
import com.victor.test.alcampokotlin.ui.MainActivity
import com.victor.test.alcampokotlin.ui.StoreActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataManagerModule::class])
interface AppComponent {
    fun plus(presenterModule: PresenterModule): PresenterComponent

    fun inject(application: Application)
}