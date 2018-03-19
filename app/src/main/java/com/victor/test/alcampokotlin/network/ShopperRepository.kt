package com.victor.test.alcampokotlin.network

import com.victor.test.alcampokotlin.network.bodies.GetShopperStateNewBody
import com.victor.test.alcampokotlin.network.responses.GetShopperStateNewResp
import io.reactivex.Observable
import retrofit2.http.*
import java.util.HashMap

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */
interface ShopperRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("shopper/GetShopperStateNew")
    fun getShopperStateNew(@QueryMap params: HashMap<String, String>): Observable<GetShopperStateNewResp>
//    fun getShopperStateNew(@ params: GetShopperStateNewBody): Observable<GetShopperStateNewResp>
}