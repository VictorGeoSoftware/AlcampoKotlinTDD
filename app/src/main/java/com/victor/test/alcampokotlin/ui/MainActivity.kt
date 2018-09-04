package com.victor.test.alcampokotlin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.victor.test.alcampokotlin.MainApplication
import com.victor.test.alcampokotlin.R
import com.victor.test.alcampokotlin.data.Constants.Companion.REQUEST_SELECT_STORE
import com.victor.test.alcampokotlin.data.Constants.Companion.STORE
import com.victor.test.alcampokotlin.data.models.StoreDto
import com.victor.test.alcampokotlin.data.models.StoreListByRegionDto
import com.victor.test.alcampokotlin.presenters.shopper.ShopperPresenter
import com.victor.test.alcampokotlin.presenters.stores.StorePresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: ParentActivity(), ShopperPresenter.ShopperView {
    @Inject lateinit var shopperPresenter: ShopperPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)

        shopperPresenter.view = this
        shopperPresenter.getShopperStateNew()


    }

    override fun onResume() {
        super.onResume()
        shopperPresenter.view = this
    }

    override fun onDestroy() {
        super.onDestroy()
        shopperPresenter.destroy()
        (application as MainApplication).releasePresenterComponent()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SELECT_STORE) {
            if (resultCode == Activity.RESULT_OK) {
                val selectedStore = data?.getParcelableExtra<StoreDto>(STORE)

                selectedStore.let {
                    txt_store_name.text = it?.label
                    Snackbar.make(main_layout, getString(R.string.msg_store_selected), Snackbar.LENGTH_SHORT).show()
                }
            } else {
                showNotStoreSnackBar()
            }
        }
    }



    // ------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- SHOPPER VIEW INTERFACE ---------------------------------------------
    override fun onStoreReceived(favouriteStore: StoreDto?) {
        txt_store_name.text = favouriteStore?.label
    }

    override fun onNoneStoreReceived() {
        txt_store_name.text = ""
        showNotStoreSnackBar()
    }

    override fun onNetworkError(exception: Throwable) {
        // TODO :: show a SnackBar or something like that
    }



    // ------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- METHODS AND RUNNABLES ----------------------------------------------
    private fun showNotStoreSnackBar() {
        val snackBar = Snackbar.make(main_layout, getString(R.string.message_select_store), Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(getString(R.string.action_select_one)) {
            val intent = Intent(this, StoreActivity::class.java)
            startActivityForResult(intent, REQUEST_SELECT_STORE)
        }
        snackBar.show()
    }
}
