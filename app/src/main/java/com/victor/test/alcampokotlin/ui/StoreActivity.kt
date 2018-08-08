package com.victor.test.alcampokotlin.ui

import android.os.Bundle
import com.victor.test.alcampokotlin.MainApplication
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.DataManager
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import kotlinx.android.synthetic.main.activity_store.*
import javax.inject.Inject

class StoreActivity : ParentActivity(), StorePresenter.StoreView, ShopperPresenter.ShopperView {

//    @Inject lateinit var shopperPresenter: ShopperPresenter
//    @Inject lateinit var dataManager: DataManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)
        (application as MainApplication).presenterComponent.inject(this)

        fabBack.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
//        shopperPresenter.view = this
        System.out.println("StoreActivity - onResume - setea View")

//        val shopperCtx = dataManager.shopperCtx
//        System.out.println("StoreActivity - onResume - shopperCtx recuperado :: $shopperCtx")

//        val params = GetStoreListByRegionBody("e5a5d18b83168cd79bea3e16ceec6976683192", 12.345, 3.124)
//        storePresenter.getStoreList(params)
    }


    override fun onDestroy() {
        super.onDestroy()
//        shopperPresenter.destroy()
//        storePresenter.destroy()
    }

    override fun onBackPressed() {
        finish()
    }
}
