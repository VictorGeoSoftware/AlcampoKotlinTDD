package com.victor.test.alcampokotlin.di

import android.content.Context
import com.victor.test.alcampokotlin.data.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 21/3/18.
 * ${APP_NAME}
 */

@Module
class DataManagerModule {
    @Provides
    @Singleton
    fun provideDataManager(context: Context): DataManager = DataManager(context)
}