package com.victor.test.alcampokotlin

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.test.alcampokotlin.data.Constants
import com.victor.test.alcampokotlin.data.DataManager
import com.victor.test.alcampokotlin.data.models.Status
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.network.ShopperRepository
import com.victor.test.alcampokotlin.network.responses.GetShopperStateNewResp
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.collections.ArrayList


/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */

@RunWith(MockitoJUnitRunner::class)
class ShopperPresenterTest: ParentUnitTest() {

    @Inject @Named("REAL_CONTEXT") lateinit var shopperRepository: ShopperRepository
    @Inject @Named("MOCKED_CONTEXT") lateinit var mockedShopperRepository: ShopperRepository


    @Mock lateinit var shopperView: ShopperPresenter.ShopperView
    @Mock lateinit var dataManager: DataManager
    private lateinit var shopperPresenter: ShopperPresenter

    private val shopperStateParams = HashMap<String, String>()
    private val mockedShopperStateResponseObs = Observable.just(GetShopperStateNewResp(false, "abc123", StoreDto(), Status(), ArrayList()))
    private lateinit var testScheduler:TestScheduler


    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        System.out.println("ShopperPresenterTest - createMockedShopperPresenter! - repos :: $shopperRepository / $mockedShopperRepository")

        MockitoAnnotations.initMocks(this)
        shopperPresenter = createMockedShopperPresenter()


        shopperStateParams["lang"] = "es_ES"
        shopperStateParams["terminalUniqueId"] = "abc123"
        shopperStateParams["platform"] = Constants.WS_PARAM_PLATFORM
        shopperStateParams["versionParam"] = Constants.WS_PARAM_VERSIONPARAM
    }

    private fun createMockedShopperPresenter(): ShopperPresenter {
        testScheduler = TestScheduler()
        val shopperPresenter = ShopperPresenter(testScheduler, testScheduler, shopperRepository, dataManager)
        shopperPresenter.view = shopperView
        return shopperPresenter
    }



    // ---------------------------------------------------------------------------------------------
    // --------------------------------------------- TEST CASES ------------------------------------
    @Test
    fun `should receive new shopperCtx value`() {

        /**
         * Important!
         * Uncommenting whenever sentence, we use mocked params and responses, otherwise, we test REAL context!!
         * So, for testing in both REAL and MOCKED cases, it's necessary to comment the corresponding provider, in TestNetworkModule class,
         * according with the context we are going to test
         */

//        whenever(shopperRepository.getShopperStateNew(shopperStateParams)).thenReturn(mockedShopperStateResponseObs)
        whenever(mockedShopperRepository.getShopperStateNew(shopperStateParams)).thenReturn(mockedShopperStateResponseObs)

        shopperPresenter.getShopperStateNew(shopperStateParams)
        testScheduler.triggerActions()

//        verify(shopperRepository, times(1)).getShopperStateNew(shopperStateParams)
        verify(mockedShopperRepository, times(1)).getShopperStateNew(shopperStateParams)
    }


    @Test
    fun `should return context value in ShopperView interface`() {

        Mockito.`when`(shopperRepository.getShopperStateNew(shopperStateParams)).thenReturn(mockedShopperStateResponseObs)

        shopperPresenter.getShopperStateNew(shopperStateParams)
        testScheduler.triggerActions()


        verify(shopperView).onContextValueReceived(any())
    }
}