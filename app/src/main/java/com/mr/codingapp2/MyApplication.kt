package com.mr.codingapp2

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.mr.codingapp2.backend.di.module.ApiModule
import com.mr.codingapp2.di.component.AppComponent
import com.mr.codingapp2.di.component.DaggerAppComponent
import com.mr.codingapp2.di.module.AppModule
import com.mr.codingapp2.di.module.FlipperModule
import com.facebook.flipper.core.FlipperClient
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Inject

open class MyApplication : Application() {
    lateinit var component: AppComponent
    @Inject
    lateinit var flipperClient: FlipperClient
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate() {
        super.onCreate()
        component = createComponent()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        flipperClient.start()
    }

    protected open fun createComponent(): AppComponent {
        val httpClientBuilder = OkHttpClient.Builder()
        val gsonBuilder = GsonBuilder()

        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .flipperModule(FlipperModule(this, httpClientBuilder))
            .apiModule(ApiModule(httpClientBuilder, gsonBuilder))
            .build()
    }
}
