package com.aulloaq.storesapp.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ConnectionInterceptor constructor(
    private val connectionHelper: ConnectionHelper
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectionHelper.isConnected()) {
            throw Exception()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}