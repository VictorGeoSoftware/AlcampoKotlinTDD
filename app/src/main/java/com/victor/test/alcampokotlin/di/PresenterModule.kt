package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */

@Module
class PresenterModule {
    @Inject lateinit var shopperRepository: ShopperRepository

    @Provides
    @Singleton
    fun provideShopperPresenter() = ShopperPresenter(AndroidSchedulers.mainThread(), Schedulers.newThread(), shopperRepository)
}