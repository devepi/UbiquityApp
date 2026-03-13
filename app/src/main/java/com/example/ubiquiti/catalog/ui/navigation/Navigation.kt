package com.example.ubiquiti.catalog.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.ubiquiti.catalog.core.model.Product
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed interface Navigation {

  @Serializable
  object ProductList : Navigation

  @Serializable
  data class ProductDetails(
    val product: Product
  ) : Navigation
}

val ProductNavType = object : NavType<Product>(
  isNullableAllowed = false
) {
  override fun put(bundle: Bundle, key: String, value: Product) {
    bundle.putParcelable(key, value)
  }

  override fun get(bundle: Bundle, key: String): Product? {
    return bundle.getParcelable(key) as Product?
  }

  override fun serializeAsValue(value: Product): String {
    return Uri.encode(Json.encodeToString(value))
  }

  override fun parseValue(value: String): Product {
    return Json.decodeFromString<Product>(value)
  }
}