package com.victor.test.alcampokotlin.di.mainactivity

import com.victor.test.alcampokotlin.di.scopes.ActivityScope
import com.victor.test.alcampokotlin.ui.MainActivity
import dagger.Subcomponent

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */

@ActivityScope
@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}