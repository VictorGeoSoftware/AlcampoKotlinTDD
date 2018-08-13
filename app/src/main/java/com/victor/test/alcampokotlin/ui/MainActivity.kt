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

        val language = Locale.getDefault().toString()
        val params = HashMap<String, String>()
        params["lang"] = language
        params["terminalUniqueId"] = UniqueId(this).getNewUniqueId()  // La pasaremos por parametro
        params["platform"] = Constants.WS_PARAM_PLATFORM
        params["versionParam"] = Constants.WS_PARAM_VERSIONPARAM
        traceObject(params)
//        btnStart.setOnClickListener { shopperPresenter.getShopperStateNew(params) }
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
        // TODO :: compobar si hay tienda seleccionada
    }




}
