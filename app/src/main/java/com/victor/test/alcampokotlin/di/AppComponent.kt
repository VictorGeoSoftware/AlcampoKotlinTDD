package com.victor.test.alcampokotlin.di

import android.app.Application
import com.victor.test.alcampokotlin.di.mainactivity.MainActivityComponent
import com.victor.test.alcampokotlin.di.mainactivity.MainActivityModule
import com.victor.test.alcampokotlin.di.scopes.ActivityScope
import com.victor.test.alcampokotlin.di.storeactivity.StoreActivityComponent
import com.victor.test.alcampokotlin.di.storeactivity.StoreActivityModule
import dagger.Component
import junit.framework.Test
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */

// https://antonioleiva.com/dagger-3/

@Singleton
//@Component(modules = [(AppModule::class), (NetworkModule::class), (PresenterModule::class)])
@Component(modules = [(AppModule::class), (NetworkModule::class)])
interface AppComponent {
    fun inject(application: Application)

//    fun plus(mainActivityModule: MainActivityModule): MainActivityComponent
//    fun plus(storeActivityModule: StoreActivityModule): StoreActivityComponent
}