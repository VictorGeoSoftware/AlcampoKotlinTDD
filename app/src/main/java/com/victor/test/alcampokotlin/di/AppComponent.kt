package com.victor.test.alcampokotlin.di

import android.app.Application
import com.victor.test.alcampokotlin.di.mainactivity.MainActivityComponent
import com.victor.test.alcampokotlin.di.mainactivity.MainActivityModule
import com.victor.test.alcampokotlin.di.storeactivity.StoreActivityComponent
import com.victor.test.alcampokotlin.di.storeactivity.StoreActivityModule
import dagger.Component
import junit.framework.Test
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (PresenterModule::class)])
interface AppComponent {
    fun inject(application: Application)

    fun plus(mainActivityModule: MainActivityModule): MainActivityComponent
    fun plus(storeActivityModule: StoreActivityModule): StoreActivityComponent
}