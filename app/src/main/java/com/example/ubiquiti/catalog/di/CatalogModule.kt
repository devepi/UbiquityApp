package com.example.ubiquiti.catalog.di

import android.content.Context
import com.example.ubiquiti.catalog.core.repository.CatalogRepository
import com.example.ubiquiti.catalog.data.api.CatalogApi
import com.example.ubiquiti.catalog.data.network.NetworkCacheInterceptor
import com.example.ubiquiti.catalog.data.repository.CatalogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatalogModule {

  private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
  }

  @Provides
  @Singleton
  fun provideCatalogRepository(
    catalogApi: CatalogApi
  ): CatalogRepository = CatalogRepositoryImpl(catalogApi, Dispatchers.IO)

  @Provides
  @Singleton
  fun provideCatalogApi(okHttpClient: OkHttpClient): CatalogApi =
    Retrofit.Builder()
      .baseUrl("https://static.ui.com/fingerprint/ui/")
      .client(okHttpClient)
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .build()
      .create(CatalogApi::class.java)

  @Provides
  @Singleton
  fun provideOkHttpClient(cache: Cache): OkHttpClient =
    OkHttpClient
      .Builder()
      .cache(cache)
      .addNetworkInterceptor(NetworkCacheInterceptor())
      .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
      })
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build()

  @Provides
  @Singleton
  fun provideCache(@ApplicationContext context: Context): Cache =
    Cache(
      File(context.cacheDir, "http_cache"),
      10L * 1024 * 1024 // 10 MB
    )
}
