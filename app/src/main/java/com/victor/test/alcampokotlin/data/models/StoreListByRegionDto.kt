package com.victor.test.alcampokotlin.data.models

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */
data class StoreListByRegionDto(val regionName:String, val expandList:Boolean, val storeList:ArrayList<StoreDto>)