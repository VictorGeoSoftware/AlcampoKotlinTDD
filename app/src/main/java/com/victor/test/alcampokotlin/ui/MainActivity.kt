package com.victor.test.alcampokotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.victor.test.alcampokotlin.MainApplication
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.Constants.Companion.REQUEST_SELECT_STORE
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import javax.inject.Inject

class MainActivity: ParentActivity(), ShopperPresenter.ShopperView {
    @Inject lateinit var shopperPresenter: ShopperPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent()

        shopperPresenter.view = this
        shopperPresenter.getShopperStateNew()
    }

    override fun onResume() {
        super.onResume()
        shopperPresenter.view = this
    }

    override fun onDestroy() {
        super.onDestroy()
        shopperPresenter.destroy()
        (application as MainApplication).releasePresenterComponent()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_STORE) {
            if (resultCode == Activity.RESULT_OK) {
                // dejar todo como está
            } else {
                // volver a pedir tienda
            }
        }
    }



    // ------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- SHOPPER VIEW INTERFACE ---------------------------------------------
    override fun onStoreReceived(favouriteStore: StoreDto?) {
        // TODO :: check if favouriteStore exist!

    }

    override fun onNoneStoreReceived() {
        val intent = Intent(this, StoreActivity::class.java)
        startActivityForResult(intent, REQUEST_SELECT_STORE)
    }

    override fun onNetworkError(exception: Throwable) {
        // TODO :: show a SnackBar or something like that
    }

}
