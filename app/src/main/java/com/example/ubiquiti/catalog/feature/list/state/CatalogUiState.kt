package com.example.ubiquiti.catalog.feature.list.state

import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.core.model.ProductFilter

sealed interface CatalogUiState {
  object Loading : CatalogUiState

  data class Error(val message: String) : CatalogUiState

  data class Success(
    val products: List<Product>,
    val filter: ProductFilter,
  ) : CatalogUiState
}
