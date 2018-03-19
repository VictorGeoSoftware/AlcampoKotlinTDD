package com.victor.test.alcampokotlin

import android.content.Context
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.data.models.WsError
import com.victor.test.alcampokotlin.network.StoreRepository
import com.victor.test.alcampokotlin.network.bodies.GetStoreListByRegionBody
import com.victor.test.alcampokotlin.network.responses.GetStoreListByRegionResp
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.Observable


/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class StorePresenterTest {

    @Mock lateinit var context: Context
    @Mock lateinit var mockView: StorePresenter.StoreView
    @Mock lateinit var storeRepository: StoreRepository
    private lateinit var storePresenter: StorePresenter
    private lateinit var testScheduler: TestScheduler

    // TODO :: averiguar como cambiar entre mocked connection y real connection
    private val storeListResp = HashMap<String, StoreListByRegionDto>()
    private val getStoreListByRegionObs = Observable.just(GetStoreListByRegionResp(storeListResp, ArrayList(), 1))


    private fun createMockPresenter(): StorePresenter {
        testScheduler = TestScheduler()
        val storePresenter = StorePresenter(testScheduler, testScheduler)
        storePresenter.view = mockView
//        storePresenter.injectedStoreRepository = storeRepository
        return storePresenter
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        storePresenter = createMockPresenter()
    }

    @Test
    fun `should perform getStoreListByRegion call`() {
        val params = GetStoreListByRegionBody("abc123", 12.345, 3.124)
        whenever(storeRepository.getStoreListByRegion(params)).thenReturn(getStoreListByRegionObs)

        storePresenter.getStoreList(params)
        testScheduler.triggerActions()

        verify(storeRepository, times(1)).getStoreListByRegion(params)
    }

    @Test
    fun `should return an error with a bad context value`() {
        val params = GetStoreListByRegionBody("abc123", 12.345, 3.124)
        whenever(storeRepository.getStoreListByRegion(params)).thenReturn(getStoreListByRegionObs)

        storePresenter.getStoreList(params)
        testScheduler.triggerActions()

        verify(mockView).onStoreListErrors("SHOPPER_CTX_FORMAT")
    }

    @Test
    fun `should return store list with good context value`() {
        val params = GetStoreListByRegionBody("e5a5d18b83168cd79bea3e16ceec6976683192", 12.345, 3.124)
        whenever(storeRepository.getStoreListByRegion(params)).thenReturn(getStoreListByRegionObs)

        storePresenter.getStoreList(params)
        testScheduler.triggerActions()

        verify(mockView).onStoreListReceived(any())
    }
}

