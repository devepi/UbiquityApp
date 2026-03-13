package com.example.ubiquiti.catalog.feature.list.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ubiquiti.catalog.R
import com.example.ubiquiti.catalog.feature.list.state.CatalogUiState
import com.example.ubiquiti.catalog.ui.shared.button.PrimaryButton
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun CatalogUiError(
  state: CatalogUiState.Error,
  onRetry: () -> Unit,
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
      Icon(
        modifier = Modifier.size(100.dp),
        painter = painterResource(id = R.drawable.img_error_24px),
        tint = Color.Red,
        contentDescription = "Error"
      )
      Spacer(Modifier.size(Theme.Spacing.xxLarge))
      Text(
        "Something went wrong",
        style = Theme.Typography.title,
        color = Theme.Colors.defaultTextColor,
        textAlign = TextAlign.Center,
      )
      Spacer(Modifier.size(Theme.Spacing.medium))
      Text(
        state.message,
        style = Theme.Typography.title,
        color = Theme.Colors.defaultTextColor,
        textAlign = TextAlign.Center,
      )
      Spacer(Modifier.size(Theme.Spacing.xxLarge))
      PrimaryButton(
        Modifier.padding(Theme.Spacing.medium),
        onClick = onRetry
      ) {
        Text(
          "Retry",
          Modifier.padding(Theme.Spacing.medium),
          style = Theme.Typography.bodyMedium,
          color = Theme.Colors.defaultTextColor
        )
      }
    }
  }
}

