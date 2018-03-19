package com.victor.test.alcampokotlin.network.responses

import com.victor.test.alcampokotlin.data.models.WsError

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */
open class ParentResponse {
    val errors: ArrayList<WsError>? = null
    val executeTime:Long? = null
}
