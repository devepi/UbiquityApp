package com.example.ubiquiti.catalog.ui.shared.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.ubiquiti.catalog.ui.theme.Theme
import kotlin.math.min

@Composable
fun GlassSurfaceContainer(
  modifier: Modifier = Modifier,
  properties: GlassSurfaceContainerProperties = GlassSurfaceContainerPropertiesDefault.values(),
  content: @Composable () -> Unit,
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
      .defaultMinSize(properties.minWidth, properties.minHeight)
      .clip(shape = Theme.Corners.medium)
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(Color(0xffececec), Color(0xfffefefe)),
        )
      )
      .drawWithCache {
        val rect = Rect(Offset.Zero, size)
        val radius = min(
          properties.maxRadius.toPx(),
          min(rect.width, rect.height) / 2f
        )
        onDrawBehind {
          drawPath(
            path = glassSurfacePathToStroke(
              rect,
              radius,
              properties.stroke.toPx()
            ),
            style = Stroke(width = properties.stroke.toPx()),
            color = Color.White,
          )
        }
      }
      .padding(properties.innerPadding)
  ) {
    content()
  }
}

fun glassSurfacePathToStroke(
  rect: Rect,
  radius: Float,
  stroke: Float = 0f,
) = Path().apply {
  val fixedRect = rect.deflate(stroke / 2f)
  val rectSize = Size(radius * 2f - stroke, radius * 2f - stroke)

  val topLeftRect = Rect(fixedRect.topLeft, rectSize)
  val topRightRect = Rect(
    fixedRect.topRight.copy(x = fixedRect.right - rectSize.width),
    rectSize.copy(height = radius)
  )
  val bottomLeftRect = Rect(
    offset = fixedRect.bottomLeft.copy(
      y = fixedRect.bottom - rectSize.height
    ),
    size = rectSize.copy(height = radius * 2f + stroke * 1.5f)
  )

  addArc(
    oval = topLeftRect,
    startAngleDegrees = 180f,
    sweepAngleDegrees = 90f
  )
  lineTo(topRightRect.left + radius, topRightRect.top)
  addArc(
    oval = topRightRect,
    startAngleDegrees = 270f,
    sweepAngleDegrees = 40f
  )
  addArc(
    oval = bottomLeftRect,
    startAngleDegrees = 150f,
    sweepAngleDegrees = 30f
  )
  lineTo(topLeftRect.left, topLeftRect.top + radius - stroke)
}

