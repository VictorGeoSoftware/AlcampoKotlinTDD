package com.victor.test.alcampokotlin.presenters.shopper

import com.victor.test.alcampokotlin.di.DaggerPresenterComponent
import com.victor.test.alcampokotlin.di.PresenterComponent
import com.victor.test.alcampokotlin.di.PresenterModule
import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.presenters.Presenter
import io.reactivex.Scheduler

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class ShopperPresenter(
        val androidSchedulers: Scheduler,
        val subscriberSchedulers: Scheduler,
        val shopperRepository: ShopperRepository): Presenter<ShopperPresenter.ShopperView>() {


    /**
     * Quitar la injeccion de dependencias de aqui y seguir.!!  :-D
     */
    val component: PresenterComponent by lazy {
        DaggerPresenterComponent.builder().presenterModule(PresenterModule()).build()
    }


    init {
        component.inject(this)
    }

    interface ShopperView {
        fun onContextValueReceived(context: String) {

        }

    }

    fun getShopperStateNew(params: HashMap<String, String>) {

        System.out.println("TEST - getShopperStateNew - entra!")

        shopperRepository.getShopperStateNew(params)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .doOnSubscribe {
                    System.out.println("TEST - getShopperStateNew - onSubscribe!")
                }
                .subscribe(
                        {
                            System.out.println("TEST - getShopperStateNew - onNext!")
                            view?.onContextValueReceived(it.shopperCtx)
                        },
                        {
                            System.out.println("TEST - getShopperStateNew - error :: " + it.localizedMessage)
                            it.printStackTrace()
                        },
                        {
                            System.out.println("TEST - getShopperStateNew - finish")
                        }
                )

    }
}

