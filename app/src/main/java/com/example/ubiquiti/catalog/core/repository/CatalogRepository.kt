package com.example.ubiquiti.catalog.core.repository

import com.example.ubiquiti.catalog.core.model.Product

interface CatalogRepository {
  suspend fun getProducts(): Result<List<Product>>
}
