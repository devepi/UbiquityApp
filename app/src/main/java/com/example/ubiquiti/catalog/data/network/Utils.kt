package com.example.ubiquiti.catalog.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend inline fun <T> resultOf(
  ioDispatcher: CoroutineDispatcher,
  crossinline block: suspend () -> T,
): Result<T> = withContext(ioDispatcher) {
  try {
    Result.success(block())
  } catch (e: IOException) {
    Result.failure(e)
  } catch (e: HttpException) {
    Result.failure(e)
  }
}
