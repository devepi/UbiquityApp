package com.example.ubiquiti.catalog.feature.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.core.model.ProductFilter
import com.example.ubiquiti.catalog.core.model.ProductLine
import com.example.ubiquiti.catalog.core.model.ProductType
import com.example.ubiquiti.catalog.core.repository.CatalogRepository
import com.example.ubiquiti.catalog.feature.list.state.CatalogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
  val catalogRepository: CatalogRepository
) : ViewModel() {
  // Pre-populated categories list
  private val categories = listOf(
    ProductType("All"),
    ProductType("Cameras", "camera"),
    // It seems that the JSON doesn't contain any IoT items
    // Keeping it as shortcut for the empty state
    ProductType("IoT", "iot"),
  )

  // Actual filter
  private var _filter = ProductFilter(categories, selectedType = categories.first())
  val filter = _filter

  // Initially it's an empty list, but is populated when JSON parsed
  private val _productLines = mutableListOf<ProductLine>()

  // All parsed product
  private var allProducts: List<Product> = listOf()

  // UI state
  private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
  val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

  val isFilterEmpty: Boolean
    get() = _filter.isEmpty

  init {
    fetch()
  }

  fun filterByCategory(category: ProductType) {
    _filter = _filter.copy(selectedType = category)
    filter()
  }

  fun filterByLine(line: ProductLine) {
    val list = _filter.selectedLines.toMutableList()
    if (_filter.selectedLines.contains(line)) {
      list.remove(line)
    } else {
      list.add(line)
    }
    _filter = _filter.copy(selectedLines = list)
    filter()
  }

  fun filter() {
    val filteredProducts = allProducts.filter {
      (_filter.selectedType?.id == null || it.type == _filter.selectedType?.id)
        && (_filter.selectedLines.isEmpty() || _filter.selectedLines.contains(it.line))
    }

    _uiState.update {
      CatalogUiState.Success(
        filteredProducts,
        _filter,
      )
    }
  }

  fun reset() {
    _filter = _filter.copy(selectedType = null, selectedLines = listOf())
    filter()
  }

  fun retry() {
    fetch()
  }

  fun fetch() {
    _uiState.value = CatalogUiState.Loading

    viewModelScope.launch {
      catalogRepository.getProducts().fold(
        onSuccess = { products ->

          val allLines = products
            .asSequence()
            .map(Product::line)
            .toSet()
            .filterNotNull()
            .toList()
            .sortedBy(ProductLine::name)

          with(_productLines) {
            clear()
            addAll(allLines)
          }

          _filter = _filter.copy(allLines = _productLines)

          allProducts = products

          filter()
        },
        onFailure = { exception ->
          _uiState.update { CatalogUiState.Error(exception.message ?: "Unknown error") }
        }
      )
    }
  }
}
