package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */

@Singleton
@Component(modules = [NetworkModule::class, PresenterModule::class])
interface PresenterComponent {
    fun inject(shopperPresenter: ShopperPresenter)
}