package com.victor.test.alcampokotlin.data.models

import android.support.annotation.Size
import java.math.BigDecimal

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */
class StoreDto {
    private var id: Long? = null

    private var precode: String? = null

    @Size(min = 1, max = 6)
    private var code: String? = null

    private var label: String? = null
    private var selfScanningAvailable: Boolean? = null
    private var thumbnail: String? = null
    private var displayLoyaltyBarcode: Boolean? = null
    private var active: Boolean? = null
    private var visible: Boolean? = null
    private var fixProductName: Boolean? = null

    @Size(min = 1, max = 255)
    private var street: String? = null

    @Size(min = 1, max = 255)
    private var city: String? = null

    @Size(min = 1, max = 16)
    private var zipCode: String? = null

    @Size(min = 1, max = 128)
    private var province: String? = null
    private var country: String? = null

    private var provinceId: Long = 0
    private var regionId: Long = 0
    private var regionDesc: String? = null

    private var longitude: BigDecimal? = null

    private var latitude: BigDecimal? = null

    @Size(min = 9, max = 22)
    private var faxNumber: String? = null

    @Size(min = 9, max = 22)
    private var phoneNumber: String? = null

    @Size(min = 1, max = 200)
    private var email: String? = null

    private var storeImage: String? = null
    private var businessHours: List<BusinessHourDto>? = null
    private var comingHolidays: List<String>? = null
    private var serviceIds: LongArray? = null
    private var turn: Boolean = false
    private var ableOnlineShopping: Boolean = false
    private var urlOnlineShopping: String? = null

    @Size(max = 500)
    private var ipAddressV4: String? = null

    @Size(min = 1, max = 500)
    private var ipBmc: String? = null

    @Size(min = 1, max = 500)
    private var ipMasterCashRegister: String? = null

    @Size(min = 1, max = 500)
    private var ipMirrorCashRegister: String? = null

    @Size(min = 1, max = 255)
    private var userCashRegister: String? = null

    @Size(min = 1, max = 255)
    private var passCashRegister: String? = null
    @Size(min = 1, max = 255)
    private var portCashRegister: String? = null
}