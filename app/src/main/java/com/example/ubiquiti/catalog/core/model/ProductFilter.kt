package com.example.ubiquiti.catalog.core.model

data class ProductFilter(
  val allTypes: List<ProductType> = listOf(),
  val allLines: List<ProductLine> = listOf(),
  val selectedType: ProductType? = null,
  val selectedLines: List<ProductLine> = listOf(),
) {
  val isEmpty: Boolean
    get() = selectedType == null && selectedLines.isEmpty()
}
