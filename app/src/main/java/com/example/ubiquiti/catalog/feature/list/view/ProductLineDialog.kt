package com.example.ubiquiti.catalog.feature.list.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ubiquiti.catalog.core.model.ProductLine
import com.example.ubiquiti.catalog.feature.list.state.CatalogUiState
import com.example.ubiquiti.catalog.feature.list.viewmodel.CatalogViewModel
import com.example.ubiquiti.catalog.ui.shared.container.GlassSurfaceContainer
import com.example.ubiquiti.catalog.ui.shared.container.GlassSurfaceContainerPropertiesDefault
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun ProductLineDialog(
  viewModel: CatalogViewModel = hiltViewModel(),
  onDismissRequest: () -> Unit,
) {
  Dialog(
    onDismissRequest = { onDismissRequest() },
  ) {
    GlassSurfaceContainer(
      modifier = Modifier
        .shadow(1.dp, Theme.Corners.medium)
        .fillMaxWidth()
        .fillMaxHeight(0.7f)
        .wrapContentHeight(),
      properties = GlassSurfaceContainerPropertiesDefault
        .values()
        .copy(maxRadius = 22.dp)
    ) {
      val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
      val success = uiState as? CatalogUiState.Success
      val allLines = success?.filter?.allLines ?: listOf()
      val selectedLines = success?.filter?.selectedLines ?: listOf()

      Log.i("ProductLineDialog", "============ allLines: ${viewModel.filter.allLines.size}")

      LazyColumn(
        Modifier.background(Color.Transparent)
      ) {
        items(allLines) { line ->
          LineRow(line, selectedLines.contains(line)) {
            viewModel.filterByLine(line)
          }
        }
      }
    }
  }
}

@Composable
fun LineRow(
  line: ProductLine,
  isSelected: Boolean,
  onClick: (ProductLine) -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick(line) },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Checkbox(
      checked = isSelected,
      onCheckedChange = { onClick(line) },
    )
    Text(
      line.name,
      style = Theme.Typography.bodyMedium,
      color = Theme.Colors.defaultTextColor
    )
  }
}
