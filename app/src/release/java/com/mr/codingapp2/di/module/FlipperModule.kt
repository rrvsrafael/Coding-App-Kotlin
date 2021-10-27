package com.mr.codingapp2.di.module

import android.app.Application
import com.mr.codingapp2.di.AppScope
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.core.FlipperClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class FlipperModule(
    private val app: Application,
    private val httpClientBuilder: OkHttpClient.Builder
) {

    @Provides
    @AppScope
    fun provideClient(): FlipperClient = AndroidFlipperClient.getInstance(app)
}
