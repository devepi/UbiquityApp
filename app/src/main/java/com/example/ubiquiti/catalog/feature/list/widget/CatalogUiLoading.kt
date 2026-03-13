package com.example.ubiquiti.catalog.feature.list.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun CatalogUiLoading() {
  Box(
    Modifier
      .fillMaxSize()
      .background(Theme.Colors.screenBackground),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator(
      color = Theme.Colors.defaultTextColor,
    )
  }
}
