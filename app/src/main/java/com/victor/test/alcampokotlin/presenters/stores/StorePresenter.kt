package com.victor.test.alcampokotlin.presenters.stores

import com.victor.test.alcampokotlin.data.DataManager
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.network.StoreRepository
import com.victor.test.alcampokotlin.network.bodies.GetStoreListByRegionBody
import com.victor.test.alcampokotlin.presenters.Presenter
import io.reactivex.Scheduler
import javax.inject.Inject
import kotlin.collections.HashMap

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class StorePresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                         private val subscriberSchedulers: Scheduler,
                                         private val storeRepository: StoreRepository,
                                         private val dataManager: DataManager): Presenter<StorePresenter.StoreView>() {

    interface StoreView {
        fun onStoreListReceived(stores: HashMap<String, StoreListByRegionDto>) {}
        fun onStoreListErrors(message: String) {}
        fun onSelectFavouriteStoreConfirmed(store: StoreDto) {}
        fun onSelectFavouriteStoreError() { }
    }

    fun getStoreList(latitude: Double, longitude: Double) {
        val params = dataManager.getStoreListByRegionParams(latitude, longitude)

        storeRepository.getStoreListByRegion(params)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
                            System.out.println("StorePresenter - response :: $it")

                            if (it.errors.isEmpty()) {
                                view?.onStoreListReceived(it.stores)
                            } else{
                                view?.onStoreListErrors(it.errors[0].code)
                            }

                        },
                        {
                            System.out.println("StorePresenter - error :: ${it.localizedMessage}")
                            it.printStackTrace()
                        },
                        {
                            System.out.println("StorePresenter - finish!")
                        }
                )
    }

    fun selectFavouriteStore(storeId: String) {
        System.out.println("StorePresenter - selectFavouriteStore :: ${dataManager.shopperCtx} $storeId")
        storeRepository.selectFavouriteStore(dataManager.shopperCtx!!, storeId)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
                            if (it.errors.isEmpty()) {
                                view?.onSelectFavouriteStoreConfirmed(it.favouriteStore)
                            } else {
                                view?.onSelectFavouriteStoreError()
                            }
                        },
                        {
                            it.printStackTrace()
                            view?.onSelectFavouriteStoreError()
                        })
    }


}