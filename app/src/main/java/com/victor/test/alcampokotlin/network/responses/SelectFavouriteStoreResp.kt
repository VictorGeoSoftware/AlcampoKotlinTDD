package com.victor.test.alcampokotlin.network.responses

import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.data.models.WsError

/**
 * Created by victorpalmacarrasco on 4/9/18.
 * ${APP_NAME}
 */
data class SelectFavouriteStoreResp(val favouriteStore:StoreDto, val errors: ArrayList<WsError>)