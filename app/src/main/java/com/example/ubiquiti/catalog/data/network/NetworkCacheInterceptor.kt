package com.example.ubiquiti.catalog.data.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkCacheInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response =
    chain
      .proceed(chain.request())
      .newBuilder()
      .header("Cache-Control", "public, max-age=300")
      .build()

}
