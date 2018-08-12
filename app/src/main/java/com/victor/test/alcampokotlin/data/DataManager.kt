package com.victor.test.alcampokotlin.data

import android.content.Context
import com.victor.test.alcampokotlin.data.models.StoreDto

/**
 * Created by victorpalmacarrasco on 21/3/18.
 * ${APP_NAME}
 */
class DataManager(context: Context) {
    var shopperCtx: String? = null
    var storeList = ArrayList<StoreDto>()
    var favouriteStore: StoreDto? = null
}