package com.example.phone_app.ui

import android.app.Application
import com.example.phone_app.network.ConnectivityInterceptor
import com.example.phone_app.network.ConnectivityInterceptorImpl
import com.example.phone_app.network.ProductApi
import com.example.phone_app.ui.controllers.*
import com.example.phone_app.ui.viewmodelfactory.HomeViewModelFactory
import com.example.phone_app.ui.viewmodelfactory.ProfileViewModelFactory
import com.example.phone_app.ui.viewmodelfactory.ShopViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class PhoneApplication:Application(), KodeinAware {
    override val kodein =  Kodein.lazy {
        import(androidXModule(this@PhoneApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        //Service
        bind() from singleton { ProductApi(instance()) }
        //controller
        bind<HomeController>() with singleton {
            HomeControllerIml(
                instance()
            )
        }
        bind<ShopController>() with singleton {
            ShopControllerImpl(
                instance()
            )
        }
        bind<ProfileController>() with singleton {
            ProfileControllerIml(
                instance()
            )
        }
        //viewModels
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { ShopViewModelFactory(instance()) }
    }
    override fun onCreate() {
        super.onCreate()

        //AndroidThreeTen
        AndroidThreeTen.init(this)
    }
}