package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.di.scopes.ViewScope
import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.network.StoreRepository
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */

@Module
class PresenterModule {

    companion object {
        const val ANDROID_SCHEDULER = "ANDROID_SCHEDULER"
        const val TASK_SCHEDULER = "TASK_SCHEDULER"
    }

    // da error de io.reactivex.Scheduler cannot be provided without an @Provides-annotated method.
    // https://android.jlelse.eu/mvp-dagger-2-rx-clean-modern-android-app-code-74f63c9a6f2f
    // https://medium.com/@kashwin95kumar/custom-scoping-in-dagger-2-for-android-9cf6030c2f8a



    @Provides
    @Named(ANDROID_SCHEDULER)
    fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(TASK_SCHEDULER)
    fun provideTaskScheduler(): Scheduler = Schedulers.newThread()



    @Provides
    @ViewScope
    fun provideStorePresenter(@Named(ANDROID_SCHEDULER) androidScheduler:Scheduler,
                              @Named(TASK_SCHEDULER) taskScheduler:Scheduler,
                              storeRepository: StoreRepository)
            = StorePresenter(androidScheduler, taskScheduler, storeRepository)

    @Provides
    @ViewScope
    fun provideShopperPresenter(@Named(ANDROID_SCHEDULER) androidScheduler:Scheduler,
                                @Named(TASK_SCHEDULER) taskScheduler:Scheduler,
                                shopperRepository: ShopperRepository)
            = ShopperPresenter(androidScheduler, taskScheduler, shopperRepository)
}