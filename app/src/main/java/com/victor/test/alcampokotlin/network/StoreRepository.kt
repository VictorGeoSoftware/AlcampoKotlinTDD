package com.victor.test.alcampokotlin.network

import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.network.bodies.GetStoreListByRegionBody
import com.victor.test.alcampokotlin.network.responses.GetStoreListByRegionResp
import com.victor.test.alcampokotlin.network.responses.SelectFavouriteStoreResp
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */
interface StoreRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @POST("storeGasStation/GetStoreListByRegion")
    fun getStoreListByRegion(@Body params: GetStoreListByRegionBody): Observable<GetStoreListByRegionResp>

    @GET("storeGasStation/SelectFavouriteStore")
    fun selectFavouriteStore(@Query("shopperCtx") shopperCtx: String,
                             @Query("idStore") idStore: String): Observable<SelectFavouriteStoreResp>
}