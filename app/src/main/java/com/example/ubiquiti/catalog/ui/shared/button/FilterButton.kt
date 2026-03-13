package com.example.ubiquiti.catalog.ui.shared.button

import androidx.compose.runtime.Composable
import com.example.ubiquiti.catalog.R

@Composable
fun FilterButton(
  onClick: () -> Unit,
) {
  SmallButton(
    id = R.drawable.btn_filter,
    description = "Favorite",
    onClick = onClick
  )
}
