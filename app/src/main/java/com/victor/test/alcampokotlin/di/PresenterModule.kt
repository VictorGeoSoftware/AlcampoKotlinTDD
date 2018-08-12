package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.data.DataManager
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
                              storeRepository: StoreRepository, dataManager: DataManager)
            = StorePresenter(androidScheduler, taskScheduler, storeRepository, dataManager)

    @Provides
    @ViewScope
    fun provideShopperPresenter(@Named(ANDROID_SCHEDULER) androidScheduler:Scheduler,
                                @Named(TASK_SCHEDULER) taskScheduler:Scheduler,
                                shopperRepository: ShopperRepository,
                                dataManager: DataManager)
            = ShopperPresenter(androidScheduler, taskScheduler, shopperRepository, dataManager)
}