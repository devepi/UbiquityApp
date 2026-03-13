package com.example.ubiquiti.catalog.ui.shared.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.takeOrElse

object GlassSurfaceContainerPropertiesDefault {
  @Composable
  fun values() = GlassSurfaceContainerProperties(
    stroke = 1.dp,
    innerPadding = 2.dp,
    minWidth = 44.dp,
    minHeight = 44.dp,
    maxRadius = 64.dp,
  )
}

@Immutable
class GlassSurfaceContainerProperties constructor(
  val stroke: Dp,
  val innerPadding: Dp,
  val minWidth: Dp,
  val minHeight: Dp,
  val maxRadius: Dp,
) {
  fun copy(
    stroke: Dp = this.stroke,
    innerPadding: Dp = this.innerPadding,
    minWidth: Dp = this.minWidth,
    minHeight: Dp = this.minHeight,
    maxRadius: Dp = this.maxRadius,
  ) = GlassSurfaceContainerProperties(
    stroke = stroke.takeOrElse { this.stroke },
    innerPadding = innerPadding.takeOrElse { this.innerPadding },
    minWidth = minWidth.takeOrElse { this.minWidth },
    minHeight = minHeight.takeOrElse { this.minHeight },
    maxRadius = maxRadius.takeOrElse { this.maxRadius },
  )

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || other !is GlassSurfaceContainerProperties) return false
    if (stroke != other.stroke) return false
    if (innerPadding != other.innerPadding) return false
    if (minWidth != other.minWidth) return false
    if (minHeight != other.minHeight) return false
    if (maxRadius != other.maxRadius) return false
    return super.equals(other)
  }

  override fun hashCode(): Int {
    var result = stroke.hashCode()
    result = 31 * result + innerPadding.hashCode()
    result = 31 * result + minWidth.hashCode()
    result = 31 * result + minHeight.hashCode()
    result = 31 * result + maxRadius.hashCode()
    return result
  }
}
