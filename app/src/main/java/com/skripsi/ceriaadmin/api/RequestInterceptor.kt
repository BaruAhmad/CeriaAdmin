package com.skripsi.ceriaadmin.api

import android.content.Context
import com.skripsi.ceriaadmin.utils.PrefManager
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(context: Context): Interceptor {
    private val sessionManager = PrefManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        sessionManager.fetchToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}