package com.example.ubiquiti.catalog.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Product(
  val id: String,
  val name: String,
  val abbrev: String,
  val shortNames: List<String>,
  val image: String,
  val thumb: String,
  val type: String,
  val line: ProductLine? = null,
) : Parcelable {

  private fun path(id: String, image: String, size: Int): String {
    return "https://images.svc.ui.com/?u=https%3A%2F%2Fstatic.ui.com%2Ffingerprin\nt%2Fui%2Fimages%2F$id%2Fdefault%2F$image.png&w=$size&q=75"
  }

  val thumbPath: String
    get() = path(id, image, 128)

  val imagePath: String
    get() = path(id, image, 640)

}
