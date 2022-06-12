package com.project.core.domain

import com.example.astroproto.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CorBaseInterceptor private constructor() : Interceptor {
    companion object {
        val interceptor get() = CorBaseInterceptor()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", BuildConfig.NASA_API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}