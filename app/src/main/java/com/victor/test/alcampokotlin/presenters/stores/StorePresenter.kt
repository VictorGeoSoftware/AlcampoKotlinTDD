package com.victor.test.alcampokotlin.presenters.stores

import com.victor.test.alcampokotlin.presenters.Presenter
import java.util.HashMap

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class StorePresenter: Presenter<StorePresenter.StoreView>() {

    interface StoreView {
        fun onStoreListReceived() {

        }
    }

    fun getStoreList() {
//        val params = HashMap<String, String>()
        // TODO :: conectar al usuario primero
//        params["shopperCtx"] = DataManager.getInstance().getShopperContext()
//        params["latitude"] = java.lang.Double.toString(latitude)
//        params["longitude"] = java.lang.Double.toString(longitude)
    }


}