package com.example.ubiquiti.catalog.feature.list.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ubiquiti.catalog.feature.list.viewmodel.CatalogViewModel
import com.example.ubiquiti.catalog.ui.shared.button.PrimaryButton
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun CatalogUiEmpty(
  viewModel: CatalogViewModel = viewModel(),
  onReset: () -> Unit,
) {
  Box(
    Modifier
      .fillMaxSize()
      .background(Theme.Colors.screenBackground)
      .padding(Theme.Spacing.large),
    contentAlignment = Alignment.Center
  ) {
    Column(
      Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Text(
        "Empty result list",
        style = Theme.Typography.title,
        color = Theme.Colors.defaultTextColor,
        textAlign = TextAlign.Center,
      )
      if (!viewModel.isFilterEmpty) {
        Text(
          "Looks like filter is applied, try resetting it",
          style = Theme.Typography.title,
          color = Theme.Colors.defaultTextColor,
          textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(Theme.Spacing.xxLarge))
        PrimaryButton(
          Modifier.padding(Theme.Spacing.medium),
          onClick = onReset
        ) {
          Text(
            "Reset filter",
            Modifier.padding(Theme.Spacing.medium),
            style = Theme.Typography.bodyMedium,
            color = Theme.Colors.defaultTextColor
          )
        }
      }
    }
  }
}
