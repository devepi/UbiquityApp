package com.example.ubiquiti.catalog.data.network

import com.example.ubiquiti.catalog.data.api.CatalogApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

  private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
  }

  private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://static.ui.com/fingerprint/ui/")
    .client(provideHttpClient())
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

  private fun provideHttpClient() = OkHttpClient
    .Builder()
//    .cache(provideCache())
    .addInterceptor(OfflineCacheInterceptor(::isNetworkAvailable))
    .addNetworkInterceptor(NetworkCacheInterceptor())
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BASIC
    })
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

  private fun isNetworkAvailable(): Boolean = true

  val catalogApi: CatalogApi = retrofit.create(CatalogApi::class.java)
}
