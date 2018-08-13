package com.victor.test.alcampokotlin.presenters.shopper

import com.victor.test.alcampokotlin.data.DataManager
import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.presenters.Presenter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class ShopperPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                           private val subscriberSchedulers: Scheduler,
                                           private val shopperRepository: ShopperRepository,
                                           private val dataManager: DataManager): Presenter<ShopperPresenter.ShopperView>() {


    interface ShopperView {
        fun onContextValueReceived() { }
        fun onNetworkError(exception: Throwable) { }
    }



    private val compositeDisposable = CompositeDisposable()





    fun getShopperStateNew(params: HashMap<String, String>) {

        compositeDisposable.add(
                shopperRepository.getShopperStateNew(params)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
                            System.out.println("ShopperPresenter - getShopperStateNew - onNext! :: ${it.shopperCtx} | $view")
                            view?.onContextValueReceived()

                            dataManager.shopperCtx = it.shopperCtx
                            dataManager.favouriteStore = it.favouriteStore
                        },
                        {
                            System.out.println("ShopperPresenter - getShopperStateNew - error :: " + it.localizedMessage)
                            it.printStackTrace()
                            view?.onNetworkError(it)
                        },
                        {
                            System.out.println("ShopperPresenter - getShopperStateNew - finish")
                        }
                ))



    }

    override fun destroy() {
        compositeDisposable.dispose()
        view = null
        System.out.println("ShopperPresenter - destroy!")
    }
}

