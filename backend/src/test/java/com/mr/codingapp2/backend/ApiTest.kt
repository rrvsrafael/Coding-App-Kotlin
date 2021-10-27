package com.mr.codingapp2.backend

import com.mr.codingapp2.backend.api.RepoApi
import com.mr.codingapp2.backend.di.module.ApiModule
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Ignore
import org.junit.Test
import javax.inject.Inject

class ApiTest {
    init {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message ->
                println(message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

        DaggerTestComponent.builder()
            .apiModule(ApiModule(httpClientBuilder, GsonBuilder()))
            .build()
            .inject(this)
    }

    @Inject
    lateinit var repoApi: RepoApi

    @Test
    @Ignore("Comment this line to test this method")
    fun listRepos() {
        repoApi.listRepos("nekocode")
            .test()
            .await()
            .assertNoErrors()
            .assertValue { repos ->
                repos.size > 0
            }
    }
}
