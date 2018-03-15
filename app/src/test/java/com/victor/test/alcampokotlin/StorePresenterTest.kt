package com.victor.test.alcampokotlin

import com.victor.test.alcampokotlin.presenters.Presenter
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by victorpalmacarrasco on 6/3/18.
 * ${APP_NAME}
 */
class StorePresenterTest {

    @Mock lateinit var mockView: StorePresenter.StoreView
    lateinit var storePresenter: StorePresenter


    private fun createMockPresenter(): StorePresenter {
        val storePresenter = StorePresenter()
        storePresenter.view = mockView
        return storePresenter
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        storePresenter = createMockPresenter()
    }

    @Test
    fun `should return a list of stores with shopperCtx`() {
        storePresenter.getStoreList()
    }
}