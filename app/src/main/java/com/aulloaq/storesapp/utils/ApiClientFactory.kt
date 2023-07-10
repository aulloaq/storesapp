package com.aulloaq.storesapp.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientFactory<T> {
    fun create(
        baseUrl: String,
        connectionHelper: ConnectionHelper,
        tClass: Class<T>
    ): T {
        val okHttpClient = createOkHttpClient(connectionHelper)
        return createRetrofitClient(baseUrl, okHttpClient, tClass)
    }

    private fun createOkHttpClient(
        connectionHelper: ConnectionHelper
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client
            .addInterceptor(ConnectionInterceptor(connectionHelper))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun createRetrofitClient(baseUrl: String, client: OkHttpClient, tClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build().create(tClass)
    }
}