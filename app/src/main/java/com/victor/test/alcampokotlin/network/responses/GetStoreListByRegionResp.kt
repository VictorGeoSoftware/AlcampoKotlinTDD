package com.victor.test.alcampokotlin.network.responses

import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.data.models.WsError

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */
data class GetStoreListByRegionResp(val stores: HashMap<String, StoreListByRegionDto>, val errors: ArrayList<WsError>, val executeTime:Long)

