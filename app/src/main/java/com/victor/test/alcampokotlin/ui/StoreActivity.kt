package com.victor.test.alcampokotlin.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.victor.test.alcampokotlin.MainApplication
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.Constants.Companion.LOCATION_PERMISSION_REQUEST
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import com.victor.test.alcampokotlin.ui.adapters.StoresAdapter
import com.victor.test.alcampokotlin.utils.traceObject
import kotlinx.android.synthetic.main.activity_store.*
import javax.inject.Inject

class StoreActivity : ParentActivity(), StorePresenter.StoreView {
    @Inject lateinit var storePresenter: StorePresenter
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var storesAdapter: StoresAdapter
    private var storesHashMap = HashMap<String, StoreListByRegionDto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        (application as MainApplication).createPresenterComponent().inject(this)

        storePresenter.view = this
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            switchLayoutVisibility(true)
            getStoreList()
        } else {
            switchLayoutVisibility(false)
        }

        lst_stores.layoutManager = LinearLayoutManager(this)
        storesAdapter = StoresAdapter(storesHashMap)
        lst_stores.adapter = storesAdapter


        btn_grant_location_permission.setOnClickListener {
            handleLocationPermission()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        storePresenter.destroy()
        (application as MainApplication).releasePresenterComponent()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST && !grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            handleLocationPermission()
        }
    }



    // -------------------------------------------------------------------------------------------------------
    // ---------------------------------------- STORE VIEW INTERFACE -----------------------------------------
    override fun onStoreListReceived(stores: HashMap<String, StoreListByRegionDto>) {
        // TODO :: pintar la lista de tiendas!
        // TODO :: muy buen proyecto, injecta presenter en instrumentation test!
        // https://github.com/mohsenoid/fyber_mobile_offers/blob/master/app/src/androidTest/java/com/mirhoseini/fyber/test/dagger/MainActivityDaggerTest.java

        storesHashMap.clear()
        storesHashMap = stores
        traceObject("onStoreListReceived :: ${storesHashMap.size}")

//        storesAdapter.notifyDataSetChanged()
        storesAdapter = StoresAdapter(storesHashMap)
        lst_stores.adapter = storesAdapter
    }

    override fun onStoreListErrors(message: String) {
        // TODO :: mostrar algún tipo de error
    }



    // -------------------------------------------------------------------------------------------------------
    // ---------------------------------------- METHODS AND RUNNABLES ----------------------------------------
    private fun handleLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            switchLayoutVisibility(true)
            getStoreList()
        } else {
            val permission = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getStoreList() {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            it?.let {
                traceObject("call to getStoreList with coordinates")
                storePresenter.getStoreList(it.latitude, it.longitude)
            } ?: kotlin.run {
                traceObject("call to getStoreList without coordinates")
                storePresenter.getStoreList(0.0, 0.0)
            }
        }
    }

    private fun switchLayoutVisibility(locationPermissions: Boolean) {
        if (locationPermissions) {
            lst_stores.visibility = View.VISIBLE
            layout_no_stores.visibility = View.GONE
        } else {
            lst_stores.visibility = View.GONE
            layout_no_stores.visibility = View.VISIBLE
        }
    }
}
