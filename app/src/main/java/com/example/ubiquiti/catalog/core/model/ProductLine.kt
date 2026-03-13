package com.example.ubiquiti.catalog.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ProductLine(
  val id: String,
  val name: String,
): Parcelable
