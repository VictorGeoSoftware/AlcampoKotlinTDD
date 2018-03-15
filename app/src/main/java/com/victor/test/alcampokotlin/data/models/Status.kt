package com.victor.test.alcampokotlin.data.models

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by victorpalmacarrasco on 9/3/18.
 * ${APP_NAME}
 */
class Status {

    companion object {
        val STATUS_NOT_YET_ACCEPTED = "SHOPPER_NOT_YET_ACCEPTED"
        val STATUS_OUT_OF_TRIP = "OUT_OF_TRIP"
        /*
        Este status es para cuando esté TRIP_STARTED,
        y tenga que evitar el modo ScanYou por tener la cesta vacía
         */
        val STATUS_FAKE_OUT_OF_TRIP = "STATUS_FAKE_OUT_OF_TRIP"
        val STATUS_TRIP_STARTED = "TRIP_STARTED"
        val STATUS_TRIP_STARTED_BEACONS = "TRIP_STARTED_BEACONS"
        val STATUS_TRIP_STARTED_DATAMATRIX = "TRIP_STARTED_DATAMATRIX"
        val STATUS_AUDIT_REQUIRED = "AUDIT_REQUIRED"
        val STATUS_WAITING_ASSISTANCE = "WAITING_ASSISTANCE"
        val STATUS_WAITING_TO_PAY = "WAITING_TO_PAY"
        val STATUS_WAITING_ACTIVATION = "WAITING_ACTIVATION"
    }


    private var code: String? = null
    private var message: String? = null


    fun getCode(): String? {
        return code
    }

    fun getMessage(): String? {
        return message
    }
}