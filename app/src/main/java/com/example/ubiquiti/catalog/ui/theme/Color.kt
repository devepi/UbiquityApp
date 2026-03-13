package com.example.ubiquiti.catalog.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Theme {
  object Colors {
    val surface = Color(0xffffffff)
    val screenBackground = Color(0xfff5f5f5)

    val defaultTextColor = Color(0xff212121)
    val secondaryTextColor = Color(0xff808893)

    val rowAccessoryIcon = Color(0xff818181)

    val rowDivider = Color(0xfff4f5f6)
  }

  object Typography {
    val bodyMedium = TextStyle(
      fontWeight = FontWeight.Normal,
      fontSize = 16.sp,
      letterSpacing = 0.5.sp
    )
    val bodySmall = TextStyle(
      fontWeight = FontWeight.Normal,
      fontSize = 12.sp,
      letterSpacing = 0.5.sp
    )
    val title = TextStyle(
      fontWeight = FontWeight.Bold,
      fontSize = 16.sp,
      letterSpacing = 0.5.sp
    )
    val heading = TextStyle(
      fontWeight = FontWeight.Bold,
      fontSize = 24.sp,
      letterSpacing = 0.5.sp
    )
  }

  object Spacing {
    val xxSmall = 2.dp
    val xSmall = 4.dp
    val small = 8.dp
    val medium = 12.dp
    val large = 16.dp
    val xLarge = 20.dp
    val xxLarge = 24.dp
  }

  object Corners {
    val medium = RoundedCornerShape(22.dp)

    fun medium(isFirst: Boolean, isLast: Boolean) = RoundedCornerShape(
      topStart = if (isFirst) medium.topStart else CornerSize(0.dp),
      topEnd = if (isFirst) medium.topEnd else CornerSize(0.dp),
      bottomStart = if (isLast) medium.bottomStart else CornerSize(0.dp),
      bottomEnd = if (isLast) medium.bottomEnd else CornerSize(0.dp),
    )
  }
}

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
