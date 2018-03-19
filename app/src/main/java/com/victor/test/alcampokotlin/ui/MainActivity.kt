package com.victor.test.alcampokotlin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.Constants
import com.victor.test.alcampokotlin.di.mainactivity.MainActivityModule
import com.victor.test.alcampokotlin.network.bodies.GetShopperStateNewBody
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.utils.UniqueId
import com.victor.test.alcampokotlin.utils.app
import com.victor.test.alcampokotlin.utils.traceObject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MainActivity: ParentActivity(), ShopperPresenter.ShopperView {

    @Inject lateinit var shopperPresenter: ShopperPresenter
    private val component by lazy { app.component.plus(MainActivityModule(this)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)




        val language = Locale.getDefault().toString()
        val params = HashMap<String, String>()
        params["lang"] = language
        params["terminalUniqueId"] = UniqueId(this).getNewUniqueId()  // La pasaremos por parametro
        params["platform"] = Constants.WS_PARAM_PLATFORM
        params["versionParam"] = Constants.WS_PARAM_VERSIONPARAM
        traceObject(params)
        btnStart.setOnClickListener { shopperPresenter.getShopperStateNew(params) }
    }

    override fun onResume() {
        super.onResume()
        shopperPresenter.view = this
    }

    override fun onDestroy() {
        super.onDestroy()
        shopperPresenter.destroy()

    }

    // ------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- SHOPPER VIEW INTERFACE ---------------------------------------------
    override fun onContextValueReceived(context: String) {
        System.out.println("MainActivity - onContextValueReceived :: $context")
        val intent = Intent(this, StoreActivity::class.java)
        startActivity(intent)
    }




}
