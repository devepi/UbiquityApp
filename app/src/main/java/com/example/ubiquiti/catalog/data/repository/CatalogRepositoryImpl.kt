package com.example.ubiquiti.catalog.data.repository

import com.example.ubiquiti.catalog.core.dto.ProductDto
import com.example.ubiquiti.catalog.core.repository.CatalogRepository
import com.example.ubiquiti.catalog.data.api.CatalogApi
import com.example.ubiquiti.catalog.data.network.resultOf
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
  private val catalogApi: CatalogApi,
  private val ioDispatcher: CoroutineDispatcher
) : CatalogRepository {
  override suspend fun getProducts() = resultOf(ioDispatcher) {
    catalogApi
      .getCatalog()
      .products
      .map(ProductDto::toDomain)
  }
}
