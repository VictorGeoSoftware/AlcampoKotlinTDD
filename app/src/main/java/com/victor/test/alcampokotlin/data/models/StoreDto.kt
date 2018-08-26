package com.victor.test.alcampokotlin.data.models

import android.support.annotation.Size
import java.math.BigDecimal

/**
 * Created by victorpalmacarrasco on 8/3/18.
 * ${APP_NAME}
 */
class StoreDto {
    var id: Long? = null

    var precode: String? = null

    @Size(min = 1, max = 6)
    var code: String? = null

    var label: String? = null
    var selfScanningAvailable: Boolean? = null
    var thumbnail: String? = null
    var displayLoyaltyBarcode: Boolean? = null
    var active: Boolean? = null
    var visible: Boolean? = null
    var fixProductName: Boolean? = null

    @Size(min = 1, max = 255)
    var street: String? = null

    @Size(min = 1, max = 255)
    var city: String? = null

    @Size(min = 1, max = 16)
    var zipCode: String? = null

    @Size(min = 1, max = 128)
    var province: String? = null
    var country: String? = null

    var provinceId: Long = 0
    var regionId: Long = 0
    var regionDesc: String? = null

    var longitude: BigDecimal? = null

    var latitude: BigDecimal? = null

    @Size(min = 9, max = 22)
    var faxNumber: String? = null

    @Size(min = 9, max = 22)
    var phoneNumber: String? = null

    @Size(min = 1, max = 200)
    var email: String? = null

    var storeImage: String? = null
    var businessHours: List<BusinessHourDto>? = null
    var comingHolidays: List<String>? = null
    var serviceIds: LongArray? = null
    var turn: Boolean = false
    var ableOnlineShopping: Boolean = false
    var urlOnlineShopping: String? = null

    @Size(max = 500)
    var ipAddressV4: String? = null

    @Size(min = 1, max = 500)
    var ipBmc: String? = null

    @Size(min = 1, max = 500)
    var ipMasterCashRegister: String? = null

    @Size(min = 1, max = 500)
    var ipMirrorCashRegister: String? = null

    @Size(min = 1, max = 255)
    var userCashRegister: String? = null

    @Size(min = 1, max = 255)
    var passCashRegister: String? = null
    @Size(min = 1, max = 255)
    var portCashRegister: String? = null
}