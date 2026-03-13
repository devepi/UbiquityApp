package com.example.ubiquiti.catalog.ui.shared.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ubiquiti.catalog.R
import com.example.ubiquiti.catalog.ui.shared.container.GlassSurfaceContainer
import com.example.ubiquiti.catalog.ui.theme.Theme
import com.example.ubiquiti.catalog.ui.theme.UbiquitiCatalogTheme

@Composable
@Preview
fun PrimaryButtonPreview() {
  UbiquitiCatalogTheme {
    PrimaryButton(onClick = {}) {
      Icon(
        painter = painterResource(id = R.drawable.btn_filter),
        tint = Color.Red,
        contentDescription = "Filter button"
      )
    }
  }
}

@Composable
fun PrimaryButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  content: @Composable () -> Unit,
) {
  GlassSurfaceContainer(
    modifier
      .shadow(1.dp, Theme.Corners.medium)
      .defaultMinSize(44.dp, 44.dp)
      .clickable { onClick() }
  ) {
    content()
  }
}

@Composable
fun SmallButton(
  @DrawableRes id: Int,
  description: String = "",
  tint: Color = Theme.Colors.defaultTextColor,
  onClick: () -> Unit,
) {
  PrimaryButton(
    Modifier.shadow(1.dp, Theme.Corners.medium),
    onClick
  ) {
    Icon(
      painter = painterResource(id = id),
      contentDescription = description,
      tint = tint,
    )
  }
}
