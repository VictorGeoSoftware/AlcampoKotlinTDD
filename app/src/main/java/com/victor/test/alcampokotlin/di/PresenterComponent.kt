package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.di.scopes.ViewScope
import com.victor.test.alcampokotlin.ui.MainActivity
import com.victor.test.alcampokotlin.ui.StoreActivity
import dagger.Subcomponent

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */

/*
    So, this is a Subcomponent! A component which inherit all dependecies of father component!
    I here we have to implement the custom scope!!
 */
@ViewScope
@Subcomponent(modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(target: MainActivity)
    fun inject(target: StoreActivity)
}