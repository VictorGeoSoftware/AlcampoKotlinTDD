package com.victor.test.alcampokotlin.data

import android.content.Context
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.utils.UniqueId
import java.util.*

/**
 * Created by victorpalmacarrasco on 21/3/18.
 * ${APP_NAME}
 */
class DataManager(context: Context) {
    var mContext = context
    var shopperCtx: String? = null
    var storeList = ArrayList<StoreDto>()
    var favouriteStore: StoreDto? = null


    fun getShopperStateNewParams(): HashMap<String, String> {
        val params = HashMap<String, String>()
        params["lang"] = Locale.getDefault().toString()
        params["terminalUniqueId"] = UniqueId(mContext).getNewUniqueId()
        params["platform"] = Constants.WS_PARAM_PLATFORM
        params["versionParam"] = Constants.WS_PARAM_VERSIONPARAM

        return params
    }
}