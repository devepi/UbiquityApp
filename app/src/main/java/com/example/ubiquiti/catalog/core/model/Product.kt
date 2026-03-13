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
  val imagePath: String
    get() = "https://images.svc.ui.com/?u=https%3A%2F%2Fstatic.ui.com%2Ffingerprin\n" +
      "t%2Fui%2Fimages%2F$id%2Fdefault%2F$image.png&w=${128}&q=75"
}
