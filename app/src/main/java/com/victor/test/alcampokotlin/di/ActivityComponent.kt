package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.di.scopes.ActivityScope
import com.victor.test.alcampokotlin.ui.MainActivity
import com.victor.test.alcampokotlin.ui.StoreActivity
import dagger.Component

/**
 * Created by victorpalmacarrasco on 20/3/18.
 * ${APP_NAME}
 */

@Component(dependencies = [AppComponent::class], modules = [PresenterModule::class])
@ActivityScope
interface ActivityComponent {
//    fun plus(mainActivityModule: MainActivityModule): MainActivityComponent
//    fun plus(storeActivityModule: StoreActivityModule): StoreActivityComponent

    fun inject(mainActivity: MainActivity)
    fun inject(storeActivity: StoreActivity)
}