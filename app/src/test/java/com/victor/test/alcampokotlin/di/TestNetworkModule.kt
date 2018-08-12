package com.victor.test.alcampokotlin.di

import com.victor.test.alcampokotlin.network.ShopperRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by victorpalmacarrasco on 12/8/18.
 * ${APP_NAME}
 */

//@Module // -> for mocked modules is necessary to remove Module annotation
class TestNetworkModule: NetworkModule() {

    override fun provideShopperRequest(retrofit: Retrofit): ShopperRepository {
//        return super.provideShopperRequest(retrofit)
        return Mockito.mock(ShopperRepository::class.java)
    }
}