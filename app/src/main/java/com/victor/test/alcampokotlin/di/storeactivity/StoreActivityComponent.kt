package com.victor.test.alcampokotlin.di.storeactivity

import com.victor.test.alcampokotlin.di.scopes.ActivityScope
import com.victor.test.alcampokotlin.ui.StoreActivity
import dagger.Subcomponent

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */

@ActivityScope
@Subcomponent(modules = [StoreActivityModule::class])
interface StoreActivityComponent {
    fun inject(storeActivity: StoreActivity)
}