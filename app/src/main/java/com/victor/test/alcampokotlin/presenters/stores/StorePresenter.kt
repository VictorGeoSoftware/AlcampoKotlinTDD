package com.victor.test.alcampokotlin.presenters.stores

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
                                         private val storeRepository: StoreRepository): Presenter<StorePresenter.StoreView>() {

    interface StoreView {
        fun onStoreListReceived(stores: HashMap<String, StoreListByRegionDto>) {}
        fun onStoreListErrors(message: String) {}
    }

    fun getStoreList(params: GetStoreListByRegionBody) {

        storeRepository.getStoreListByRegion(params)
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
                            System.out.println("StorePresenter - response :: $it")
                            // TODO :: hacer clase DataManager para almacenar tiendas

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


}