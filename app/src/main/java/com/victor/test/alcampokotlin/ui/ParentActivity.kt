package com.victor.test.alcampokotlin.ui

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.victor.test.alcampokotlin.di.ActivityComponent
import com.victor.test.alcampokotlin.di.DaggerActivityComponent
import com.victor.test.alcampokotlin.utils.app

/**
 * Created by victorpalmacarrasco on 19/3/18.
 * ${APP_NAME}
 */
open class ParentActivity: AppCompatActivity(), View.OnClickListener {

    val component: ActivityComponent by lazy {
        DaggerActivityComponent.builder().appComponent(app.component).build()
    }


    override fun onClick(p0: View?) {

    }
}