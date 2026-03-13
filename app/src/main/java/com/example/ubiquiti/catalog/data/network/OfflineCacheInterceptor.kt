package com.example.ubiquiti.catalog.data.network

import okhttp3.Interceptor
import okhttp3.Response

class OfflineCacheInterceptor(
  private val isNetworkAvailable: () -> Boolean
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {

    var request = chain.request()

    if (!isNetworkAvailable()) {

      request = request.newBuilder()
        .header(
          "Cache-Control",
          "public, only-if-cached, max-stale=604800"
        )
        .build()
    }

    return chain.proceed(request)
  }
}
