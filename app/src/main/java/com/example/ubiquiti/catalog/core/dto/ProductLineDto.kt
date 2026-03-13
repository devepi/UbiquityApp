package com.example.ubiquiti.catalog.core.dto

import com.example.ubiquiti.catalog.core.model.ProductLine
import kotlinx.serialization.Serializable

@Serializable
data class ProductLineDto(
  val id: String,
  val name: String,
) {
  fun toDomain() = ProductLine(
    id,
    name,
  )
}
