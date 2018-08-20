package com.victor.test.alcampokotlin.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.victor.test.alcampokotlin.MainApplication
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.Constants.Companion.LOCATION_PERMISSION_REQUEST
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import javax.inject.Inject

class StoreActivity : ParentActivity(), StorePresenter.StoreView {
    @Inject lateinit var storePresenter: StorePresenter
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        (application as MainApplication).createPresenterComponent()

        storePresenter.view = this
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        handleCurrentPosition()

        // TODO :: meter BDD para caso de primer arranque de app

    }

    override fun onDestroy() {
        super.onDestroy()
        storePresenter.destroy()
        (application as MainApplication).releasePresenterComponent()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            handleCurrentPosition()
        }
    }



    // -------------------------------------------------------------------------------------------------------
    // ---------------------------------------- STORE VIEW INTERFACE -----------------------------------------




    // -------------------------------------------------------------------------------------------------------
    // ---------------------------------------- METHODS AND RUNNABLES ----------------------------------------
    private fun handleCurrentPosition() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                it?.let {
                    storePresenter.getStoreList(it.latitude, it.longitude)
                } ?: kotlin.run {
                    storePresenter.getStoreList(0.0, 0.0)
                }
            }
        } else {
            val permission = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST)
        }
    }
}
