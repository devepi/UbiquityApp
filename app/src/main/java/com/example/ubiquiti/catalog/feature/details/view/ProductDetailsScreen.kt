package com.example.ubiquiti.catalog.feature.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.feature.details.row.ImageRow
import com.example.ubiquiti.catalog.feature.details.row.InfoRow
import com.example.ubiquiti.catalog.ui.shared.button.BackButton
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun ProductDetailsScreen(
  navController: NavController,
  product: Product,
) {
  Scaffold(
    Modifier
      .fillMaxSize()
      .background(Theme.Colors.screenBackground)
      .windowInsetsPadding(WindowInsets.systemBars)
  ) { innerPadding ->
    Box(
      Modifier
        .fillMaxSize()
        .background(Theme.Colors.screenBackground)
        .padding(innerPadding)

    ) {
      Column(
        Modifier
          .background(Theme.Colors.screenBackground)
      ) {
        Row(
          Modifier.padding(Theme.Spacing.medium),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          BackButton(
            onClick = { navController.popBackStack() }
          )
          Spacer(Modifier.size(Theme.Spacing.xSmall))
          Text(
            product.name,
            Modifier
              .weight(1f)
              .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Theme.Typography.title,
            color = Theme.Colors.defaultTextColor,
          )
          Spacer(Modifier.size(Theme.Spacing.xSmall))
          Spacer(Modifier.size(50.dp))
        }
        Column(
          Modifier
            .padding(
              horizontal = Theme.Spacing.large,
            )
        ) {
          ImageRow(product)
          InfoRow(
            title = "Product Line",
            description = product.line?.name ?: "",
          )
          HorizontalDivider(color = Theme.Colors.rowDivider)
          InfoRow(
            title = "ID",
            description = product.id,
          )
          HorizontalDivider(color = Theme.Colors.rowDivider)
          InfoRow(
            title = "Name",
            description = product.name,
          )
          HorizontalDivider(color = Theme.Colors.rowDivider)
          InfoRow(
            title = "Abbrev",
            description = product.abbrev,
          )
          HorizontalDivider(color = Theme.Colors.rowDivider)
          product.shortNames.forEachIndexed { index, shortName ->
            InfoRow(
              title = "Short Name",
              description = shortName,
              isLast = index == product.shortNames.lastIndex,
            )
            if (index != product.shortNames.lastIndex)
              HorizontalDivider(color = Theme.Colors.rowDivider)
          }
        }
      }
    }
  }
}
