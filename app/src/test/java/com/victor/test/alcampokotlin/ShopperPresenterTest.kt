package com.victor.test.alcampokotlin

import android.content.Context
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.test.alcampokotlin.data.Constants
import com.victor.test.alcampokotlin.data.models.Status
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.network.responses.GetShopperStateNewResp
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.utils.UniqueId
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class ShopperPresenterTest {

    @Mock lateinit var context: Context
    @Mock lateinit var shopperView: ShopperPresenter.ShopperView
    @Mock lateinit var shopperRepository: ShopperRepository
    private lateinit var shopperPresenter: ShopperPresenter

    private val shopperStateParams = HashMap<String, String>()
    private val mockedShopperStateResponseObs = Observable.just(GetShopperStateNewResp(false, "abc123", StoreDto(), Status(), ArrayList()))
    private lateinit var testScheduler:TestScheduler


    private fun createMockedShopperPresenter(): ShopperPresenter {
        testScheduler = TestScheduler()
        val shopperPresenter = ShopperPresenter(testScheduler, testScheduler, shopperRepository)
        shopperPresenter.view = shopperView
        return shopperPresenter
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        shopperPresenter = createMockedShopperPresenter()

        val lang = "es_ES" // -> hay que pillarlo desde clase con contexto
        shopperStateParams["lang"] = lang
        shopperStateParams["terminalUniqueId"] = UniqueId(context).getNewUniqueId()  // La pasaremos por parametro
        shopperStateParams["platform"] = Constants.WS_PARAM_PLATFORM
        shopperStateParams["versionParam"] = Constants.WS_PARAM_VERSIONPARAM
    }


    @Test
    fun `should receive new shopperCtx value`() {

        whenever(shopperRepository.getShopperStateNew(shopperStateParams)).thenReturn(mockedShopperStateResponseObs)

        shopperRepository.getShopperStateNew(shopperStateParams)

        shopperPresenter.getShopperStateNew(shopperStateParams)


        verify(shopperRepository, times(1)).getShopperStateNew(shopperStateParams)
    }


    @Test
    fun `should return context value in ShopperView interface`() {

        Mockito.`when`(shopperRepository.getShopperStateNew(shopperStateParams)).thenReturn(mockedShopperStateResponseObs)

        shopperPresenter.getShopperStateNew(shopperStateParams)
        testScheduler.triggerActions()


        verify(shopperView).onContextValueReceived("abc123")
    }
}