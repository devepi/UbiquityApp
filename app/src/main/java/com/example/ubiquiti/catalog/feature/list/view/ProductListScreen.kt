package com.example.ubiquiti.catalog.feature.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.feature.list.state.CatalogUiState
import com.example.ubiquiti.catalog.feature.list.viewmodel.CatalogViewModel
import com.example.ubiquiti.catalog.feature.list.widget.CatalogUiEmpty
import com.example.ubiquiti.catalog.feature.list.widget.CatalogUiError
import com.example.ubiquiti.catalog.feature.list.widget.CatalogUiLoading
import com.example.ubiquiti.catalog.feature.list.widget.CatalogUiSuccess
import com.example.ubiquiti.catalog.ui.ext.SetDartStatusBar
import com.example.ubiquiti.catalog.ui.navigation.Navigation
import com.example.ubiquiti.catalog.ui.shared.button.FilterButton
import com.example.ubiquiti.catalog.ui.shared.segments.SegmentedControl
import com.example.ubiquiti.catalog.ui.shared.segments.SegmentedControlItem
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun ProductListScreen(
  navController: NavController,
  viewModel: CatalogViewModel = hiltViewModel()
) {
  val openProductLineDialog = remember { mutableStateOf(false) }
  if (openProductLineDialog.value)
    ProductLineDialog {
      openProductLineDialog.value = false
    }
  SetDartStatusBar()
  Scaffold(
    Modifier
      .fillMaxSize()
      .background(Theme.Colors.screenBackground)
      .windowInsetsPadding(WindowInsets.systemBars)
  ) { innerPadding ->
    Box(
      Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ) {
      Column(
        Modifier
          .background(Theme.Colors.screenBackground)
          .padding(Theme.Spacing.medium)
          .fillMaxWidth(),
      ) {
        val category = remember { mutableStateOf(viewModel.filter.selectedType) }
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        if (uiState !is CatalogUiState.Error) {
          Row(
            Modifier.fillMaxWidth()
          ) {
            SegmentedControl(
              Modifier
                .weight(1f)
                .shadow(1.dp, Theme.Corners.medium),
              selectedIndex = viewModel.filter.allTypes.indexOf(category.value),
              onItemSelected = { index ->
                category.value = viewModel.filter.allTypes[index]
                viewModel.filterByCategory(viewModel.filter.allTypes[index])
              },
              items = viewModel.filter.allTypes.map { c -> SegmentedControlItem(c.name) }
            )
            Spacer(Modifier.size(Theme.Spacing.xSmall))
            FilterButton {
              openProductLineDialog.value = true
            }
          }
          Spacer(Modifier.height(Theme.Spacing.large))
        }
        when (uiState) {
          is CatalogUiState.Loading -> CatalogUiLoading()
          is CatalogUiState.Error -> CatalogUiError(uiState, onRetry = viewModel::retry)
          is CatalogUiState.Success -> SuccessView(
            products = uiState.products,
            onFilterReset = {
              viewModel.reset()
              category.value = viewModel.filter.selectedType
            },
            onDetailsClick = { product ->
              navController.navigate(Navigation.ProductDetails(product))
            }
          )
        }
      }
    }
  }
}

@Composable
fun SuccessView(
  products: List<Product>,
  onFilterReset: () -> Unit,
  onDetailsClick: (Product) -> Unit,
) {
  if (products.isEmpty()) {
    CatalogUiEmpty(onReset = onFilterReset)
  } else {
    CatalogUiSuccess(products, onDetailsClick)
  }
}