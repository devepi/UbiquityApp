package com.example.ubiquiti.catalog.feature.list.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.feature.list.row.ProductRow
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun CatalogUiSuccess(
  products: List<Product>,
  onClick: (Product) -> Unit,
) {
  LazyColumn {
    itemsIndexed(products) { index, product ->
      ProductRow(
        product,
        index == 0,
        index == products.lastIndex,
        onClick
      )
      if (index < products.lastIndex)
        HorizontalDivider(
          thickness = 1.dp,
          color = Theme.Colors.rowDivider
        )
    }
  }
}
