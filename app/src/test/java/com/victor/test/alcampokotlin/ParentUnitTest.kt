package com.victor.test.alcampokotlin

import com.victor.test.alcampokotlin.di.NetworkComponent
import com.victor.test.alcampokotlin.di.NetworkModule
import com.victor.test.alcampokotlin.di.TestNetworkModule
import dagger.Component
import org.junit.Before
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 12/8/18.
 * ${APP_NAME}
 */

// References: https://josiassena.com/dependency-injection-in-in-unit-testing/
// https://github.com/codepath/android_guides/wiki/Dependency-Injection-with-Dagger-2
open class ParentUnitTest {
    open lateinit var testNetworkComponent: TestNetworkComponent


    @Singleton
    @Component(modules = [NetworkModule::class])
    interface TestNetworkComponent: NetworkComponent {
        fun inject(target: ShopperPresenterTest)
    }

    @Before
    open fun setUp() {
        testNetworkComponent = DaggerParentUnitTest_TestNetworkComponent.builder()
                .networkModule(TestNetworkModule())
                .build()
    }
}