package com.mr.codingapp2.backend.di.module

import com.mr.codingapp2.backend.api.RepoApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(
    private val httpClientBuilder: OkHttpClient.Builder,
    private val gsonBuilder: GsonBuilder,
    private val env: Env = Env.PRODUCT
) {

    enum class Env(val baseUrl: String) {
        PRODUCT("https://api.github.com"),
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = httpClientBuilder
        .addInterceptor { chain ->
            val oldReq = chain.request()
            val newReqBuilder = oldReq.newBuilder()

            // Add headers
            newReqBuilder.addHeader(
                "previews",
                "mercy-preview"
            )

            chain.proceed(newReqBuilder.build())
        }
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = gsonBuilder.create()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(env.baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRepoApi(retrofit: Retrofit): RepoApi = retrofit.create(RepoApi::class.java)
}
