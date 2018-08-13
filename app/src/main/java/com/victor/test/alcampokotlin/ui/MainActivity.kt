package com.victor.test.alcampokotlin.ui

import android.content.Intent
import android.os.Bundle
import com.victor.test.alcampokotlin.MainApplication
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.Constants
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.utils.UniqueId
import com.victor.test.alcampokotlin.utils.traceObject
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MainActivity: ParentActivity(), ShopperPresenter.ShopperView {

    @Inject lateinit var shopperPresenter: ShopperPresenter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).presenterComponent.inject(this)

        shopperPresenter.view = this
        shopperPresenter.getShopperStateNew()
    }

    override fun onResume() {
        super.onResume()
        shopperPresenter.view = this
        System.out.println("MainActivity - onResume - setea View")
    }

    override fun onDestroy() {
        super.onDestroy()
        shopperPresenter.destroy()

    }

    // ------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- SHOPPER VIEW INTERFACE ---------------------------------------------
    override fun onContextValueReceived() {
        // TODO :: check if favouriteStore exist!
    }

    override fun onNetworkError(exception: Throwable) {

    }



}
