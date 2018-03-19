package com.victor.test.alcampokotlin.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.di.storeactivity.StoreActivityModule
import com.victor.test.alcampokotlin.network.bodies.GetStoreListByRegionBody
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import com.victor.test.alcampokotlin.utils.app
import kotlinx.android.synthetic.main.activity_store.*
import javax.inject.Inject

class StoreActivity : AppCompatActivity(), StorePresenter.StoreView, ShopperPresenter.ShopperView {

//    @Inject lateinit var storePresenter: StorePresenter
    @Inject lateinit var shopperPresenter: ShopperPresenter
    private val component by lazy { app.component.plus(StoreActivityModule(this)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)
        component.inject(this)

        fabBack.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        shopperPresenter.view = this

//        val params = GetStoreListByRegionBody("e5a5d18b83168cd79bea3e16ceec6976683192", 12.345, 3.124)
//        storePresenter.getStoreList(params)
    }


    override fun onDestroy() {
        super.onDestroy()
        shopperPresenter.destroy()
//        storePresenter.destroy()
    }

    override fun onBackPressed() {
        finish()
    }
}
