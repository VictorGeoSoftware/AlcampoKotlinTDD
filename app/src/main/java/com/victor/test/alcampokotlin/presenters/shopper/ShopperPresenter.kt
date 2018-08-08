package com.victor.test.alcampokotlin.presenters.shopper

import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.presenters.Presenter
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class ShopperPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                           private val subscriberSchedulers: Scheduler,
                                           private val shopperRepository: ShopperRepository): Presenter<ShopperPresenter.ShopperView>() {


    interface ShopperView {
        fun onContextValueReceived(context: String) { }
    }




    fun getShopperStateNew(params: HashMap<String, String>) {

        System.out.println("ShopperPresenter - getShopperStateNew - entra!")

        shopperRepository.getShopperStateNew(params)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .doOnSubscribe {
                    System.out.println("ShopperPresenter - getShopperStateNew - onSubscribe!")
                }
                .subscribe(
                        {
                            System.out.println("ShopperPresenter - getShopperStateNew - onNext! :: ${it.shopperCtx} | $view")
                            // TODO :: habria que almacenar el resto de parametros
                            view?.onContextValueReceived(it.shopperCtx)
                        },
                        {
                            System.out.println("ShopperPresenter - getShopperStateNew - error :: " + it.localizedMessage)
                            it.printStackTrace()
                        },
                        {
                            System.out.println("ShopperPresenter - getShopperStateNew - finish")
                        }
                )

    }

    override fun destroy() {
        view = null
        System.out.println("ShopperPresenter - destroy!")
    }
}

