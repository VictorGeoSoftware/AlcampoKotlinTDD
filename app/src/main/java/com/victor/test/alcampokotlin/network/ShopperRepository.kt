package com.victor.test.alcampokotlin.network

import com.victor.test.alcampokotlin.network.responses.GetShopperStateNewResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import java.util.HashMap

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */
interface ShopperRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("alcampoApp/services/shopper/GetShopperStateNew")
    fun getShopperStateNew(@QueryMap params: HashMap<String, String>): Observable<GetShopperStateNewResp>
}