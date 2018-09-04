package com.victor.test.alcampokotlin

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.test.alcampokotlin.data.DataManager
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.network.StoreRepository
import com.victor.test.alcampokotlin.network.bodies.GetStoreListByRegionBody
import com.victor.test.alcampokotlin.network.responses.GetStoreListByRegionResp
import com.victor.test.alcampokotlin.network.responses.SelectFavouriteStoreResp
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.inject.Inject


/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */


class StorePresenterTest: ParentUnitTest() {

    @Mock lateinit var mockView: StorePresenter.StoreView
//    @Mock lateinit var storeRepository: StoreRepository
    @Inject lateinit var storeRepository: StoreRepository
    @Mock lateinit var dataManager: DataManager
    private lateinit var storePresenter: StorePresenter
    private lateinit var testScheduler: TestScheduler

    // TODO :: averiguar como cambiar entre mocked connection y real connection
    private val storeListResp = HashMap<String, StoreListByRegionDto>()
    private val getStoreListByRegionObs = Observable.just(GetStoreListByRegionResp(storeListResp, ArrayList(), 1))


    private fun createMockPresenter(): StorePresenter {
        testNetworkComponent.inject(this)

        testScheduler = TestScheduler()

        val storePresenter = StorePresenter(testScheduler, testScheduler, storeRepository, dataManager)
        storePresenter.view = mockView
        return storePresenter
    }


    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        storePresenter = createMockPresenter()
    }

    @Test
    fun `should perform getStoreListByRegion call`() {
        val params = GetStoreListByRegionBody("abc123", 12.345, 3.124)
        whenever(dataManager.getStoreListByRegionParams(12.345, 3.124)).thenReturn(params)
        whenever(storeRepository.getStoreListByRegion(params)).thenReturn(getStoreListByRegionObs)

        storePresenter.getStoreList(12.345, 3.124)
        testScheduler.triggerActions()

        verify(storeRepository, times(1)).getStoreListByRegion(params)
    }

    @Test
    fun `should return an error with a bad context value`() {
        val params = GetStoreListByRegionBody("abc123", 12.345, 3.124)
        whenever(dataManager.getStoreListByRegionParams(12.345, 3.124)).thenReturn(params)
        whenever(storeRepository.getStoreListByRegion(params)).thenReturn(getStoreListByRegionObs)

        storePresenter.getStoreList(12.345, 3.124)
        testScheduler.triggerActions()

        verify(mockView).onStoreListErrors("SHOPPER_CTX_FORMAT")
    }

    @Test
    fun `should return store list with good context value`() {
        val params = GetStoreListByRegionBody("e5a5d18b83168cd79bea3e16ceec6976683192", 12.345, 3.124)
        whenever(dataManager.getStoreListByRegionParams(12.345, 3.124)).thenReturn(params)
        whenever(storeRepository.getStoreListByRegion(params)).thenReturn(getStoreListByRegionObs)

        storePresenter.getStoreList(12.345, 3.124)
        testScheduler.triggerActions()

        verify(mockView).onStoreListReceived(any())
    }

    @Test
    fun `should notify to server the favourite store and confirm operation`() {
        val storeId = "604"
        whenever(dataManager.shopperCtx).thenReturn("5ee41b9c61d8b78485572d7161d7c36d1055358")

        storePresenter.selectFavouriteStore(storeId)
        testScheduler.triggerActions()

        verify(mockView).onSelectFavouriteStoreConfirmed(any())
    }

    @Test
    fun `should notify to server the favourite store and retrieve a server error`() {
        val storeId = "604"
        whenever(dataManager.shopperCtx).thenReturn("e5a5d18b83168cd79bea3e16ceec6976683192")
//        whenever(dataManager.shopperCtx).thenReturn("5ee41b9c61d8b78485572d7161d7c36d1055358")

        storePresenter.selectFavouriteStore(storeId)
        testScheduler.triggerActions()

        verify(mockView).onSelectFavouriteStoreError()
    }

    @Test
    fun `should notify to server the favourite store and retrieve an error`() {
        val storeId = "604"
        val shopperCtx = "e5a5d18b83168cd79bea3e16ceec6976683192"
        val throwable = Throwable()
        val error = Observable.error<SelectFavouriteStoreResp>(throwable)
        whenever(storeRepository.selectFavouriteStore(shopperCtx, storeId)).thenReturn(error)

        storePresenter.selectFavouriteStore(storeId)
        testScheduler.triggerActions()

        verify(mockView).onSelectFavouriteStoreError()
    }
}

