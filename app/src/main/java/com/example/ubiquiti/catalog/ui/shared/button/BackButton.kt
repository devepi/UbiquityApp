package com.example.ubiquiti.catalog.ui.shared.button

import androidx.compose.runtime.Composable
import com.example.ubiquiti.catalog.R

@Composable
fun BackButton(
  onClick: () -> Unit,
) {
  SmallButton(
    id = R.drawable.btn_back,
    description = "Back button",
    onClick = onClick
  )
}
