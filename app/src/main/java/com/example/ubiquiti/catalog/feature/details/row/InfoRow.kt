package com.example.ubiquiti.catalog.feature.details.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun InfoRow(
  title: String,
  description: String,
  isFirst: Boolean = false,
  isLast: Boolean = false,
) {
  Box(
    Modifier
      .fillMaxWidth()
      .background(
        Theme.Colors.surface,
        shape = Theme.Corners.medium(isFirst, isLast)
      )
      .padding(Theme.Spacing.medium)
  ) {
    Row(
      Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = title,
        color = Theme.Colors.defaultTextColor,
        style = Theme.Typography.bodyMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
      )
      Spacer(
        modifier = Modifier
          .width(Theme.Spacing.medium)
      )
      Spacer(
        modifier = Modifier
          .weight(1f)
      )
      Text(
        text = description,
        color = Theme.Colors.defaultTextColor,
        style = Theme.Typography.bodyMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.End,
      )
    }
  }
}
