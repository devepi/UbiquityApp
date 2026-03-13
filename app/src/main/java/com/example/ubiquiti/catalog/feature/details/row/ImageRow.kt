package com.example.ubiquiti.catalog.feature.details.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ImageRow(
  product: Product,
) {
  Box(
    Modifier
      .fillMaxWidth()
      .background(
        Theme.Colors.surface,
        shape = Theme.Corners.medium(true)
      )
      .padding(Theme.Spacing.medium)
  ) {
    Row(
      Modifier.fillMaxWidth()
        .height(300.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(product.imagePath)
          .crossfade(true)
          .build(),
        loading = {
          CircularProgressIndicator(
            color = Theme.Colors.defaultTextColor,
          )
        },
        contentDescription = "Product image",
        error = {
          Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.img_broken_image_24px),
            contentDescription = "Product image",
          )
        }
      )
    }
  }
}
