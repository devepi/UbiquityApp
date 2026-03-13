package com.example.ubiquiti.catalog.feature.list.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.ubiquiti.catalog.R
import com.example.ubiquiti.catalog.core.model.Product
import com.example.ubiquiti.catalog.ui.theme.Theme

@Composable
fun ProductRow(
  product: Product,
  isFirst: Boolean,
  isLast: Boolean,
  onClick: (Product) -> Unit,
) {
  val cornerShape = Theme
    .Corners
    .medium(isFirst, isLast)
  Box(
    Modifier
      .fillMaxWidth()
      .background(
        Theme.Colors.surface,
        shape = cornerShape
      )
      .clip(cornerShape)
      .clickable { onClick(product) }
      .padding(Theme.Spacing.medium)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Box(
        Modifier
          .width(40.dp)
          .height(40.dp),
        contentAlignment = Alignment.Center
      ) {
        SubcomposeAsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data(product.imagePath)
            .crossfade(true)
            .build(),
          loading = {
            CircularProgressIndicator(
              color = Theme.Colors.defaultTextColor,
              modifier = Modifier
                .width(20.dp)
                .height(20.dp)
            )
          },
          contentDescription = "Product image",
          error = {
            Icon(
              painter = painterResource(R.drawable.img_broken_image_24px),
              contentDescription = "Product image",
            )
          }
        )
      }
      Spacer(Modifier.size(Theme.Spacing.medium))
      Column(Modifier.weight(1f)) {
        Text(
          text = product.name,
          color = Theme.Colors.defaultTextColor,
          style = Theme.Typography.bodyMedium,
        )
        Text(
          text = product.abbrev,
          color = Theme.Colors.secondaryTextColor,
          style = Theme.Typography.bodySmall,
        )
      }
      Spacer(Modifier.size(Theme.Spacing.medium))
      Icon(
        painter = painterResource(id = R.drawable.img_chevron_right),
        contentDescription = "Row accessory icon",
        tint = Theme.Colors.rowAccessoryIcon,
      )
    }

  }
}
