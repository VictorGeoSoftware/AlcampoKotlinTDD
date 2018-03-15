package com.victor.test.alcampokotlin.network.responses

import com.victor.test.alcampokotlin.data.models.Status
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.data.models.WsError

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */
data class GetShopperStateNewResp(
        val updateRequired: Boolean,
        val shopperCtx: String,
        val favouriteStore: StoreDto,
        val status: Status,
        val errors: ArrayList<WsError>)