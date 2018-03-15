package com.victor.test.alcampokotlin.di

import android.app.Application
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
}