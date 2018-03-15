package com.victor.test.alcampokotlin.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}