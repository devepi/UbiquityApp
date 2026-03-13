package com.example.ubiquiti.catalog.ui.shared.segments

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.takeOrElse

object SegmentedControlPropertiesDefault {
  @Composable
  fun values() = SegmentedControlProperties(
    containerPadding = 2.dp,
    containerRadius = 22.dp,
    indicatorVerticalPadding = 10.dp,
    indicatorHorizontalPadding = 2.dp,
    labelColor = Color(0xFF50565e),
    selectedLabelColor = Color(0xFF006fff),
    labelFontSize = 13.sp,
    labelFontWeight = FontWeight.Medium,
    animationDurationMillis = 300,
    easing = FastOutSlowInEasing,
    indicatorRadius = 40.dp,
    indicatorColor = Color(0xffededed),
    backgroundBrush = Brush.verticalGradient(
      colors = listOf(Color(0xffdadada), Color(0xffe6e6e6)),
    ),
  )
}

@Immutable
class SegmentedControlProperties(
  val containerPadding: Dp,
  val containerRadius: Dp,
  val indicatorVerticalPadding: Dp,
  val indicatorHorizontalPadding: Dp,
  val labelColor: Color,
  val selectedLabelColor: Color,
  val labelFontSize: TextUnit,
  val labelFontWeight: FontWeight,
  val animationDurationMillis: Int,
  val easing: Easing,
  val indicatorRadius: Dp,
  val indicatorColor: Color,
  val backgroundBrush: Brush,
) {
  fun copy(
    containerPadding: Dp = this.containerPadding,
    containerRadius: Dp = this.containerRadius,
    indicatorVerticalPadding: Dp = this.indicatorVerticalPadding,
    indicatorHorizontalPadding: Dp = this.indicatorVerticalPadding,
    labelFontSize: TextUnit = this.labelFontSize,
    labelFontWeight: FontWeight = this.labelFontWeight,
    labelColor: Color = this.labelColor,
    selectedLabelColor: Color = this.selectedLabelColor,
    animationDurationMillis: Int = this.animationDurationMillis,
    easing: Easing = this.easing,
    indicatorRadius: Dp = this.indicatorRadius,
    indicatorColor: Color = this.indicatorColor,
    backgroundBrush: Brush = this.backgroundBrush,
  ) = SegmentedControlProperties(
    containerPadding = containerPadding.takeOrElse { this.containerPadding },
    containerRadius = containerRadius.takeOrElse { this.containerRadius },
    indicatorVerticalPadding = indicatorVerticalPadding.takeOrElse { this.indicatorVerticalPadding },
    indicatorHorizontalPadding = indicatorHorizontalPadding.takeOrElse { this.indicatorHorizontalPadding },
    labelFontSize = labelFontSize.takeOrElse { this.labelFontSize },
    labelFontWeight = labelFontWeight,
    labelColor = labelColor.takeOrElse { this.labelColor },
    selectedLabelColor = selectedLabelColor.takeOrElse { this.selectedLabelColor },
    animationDurationMillis = animationDurationMillis,
    easing = easing,
    indicatorRadius = indicatorRadius.takeOrElse { this.indicatorRadius },
    indicatorColor = indicatorColor.takeOrElse { this.indicatorColor },
    backgroundBrush = backgroundBrush,
  )

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || other !is SegmentedControlProperties) return false
    if (containerPadding != other.containerPadding) return false
    if (containerRadius != other.containerRadius) return false
    if (indicatorVerticalPadding != other.indicatorVerticalPadding) return false
    if (indicatorHorizontalPadding != other.indicatorHorizontalPadding) return false
    if (labelFontSize != other.labelFontSize) return false
    if (labelFontWeight != other.labelFontWeight) return false
    if (labelColor != other.labelColor) return false
    if (selectedLabelColor != other.selectedLabelColor) return false
    if (animationDurationMillis != other.animationDurationMillis) return false
    if (easing != other.easing) return false
    if (indicatorRadius != other.indicatorRadius) return false
    if (indicatorColor != other.indicatorColor) return false
    if (backgroundBrush != other.backgroundBrush) return false
    return super.equals(other)
  }

  override fun hashCode(): Int {
    var result = containerPadding.hashCode()
    result = 31 * result + containerRadius.hashCode()
    result = 31 * result + indicatorVerticalPadding.hashCode()
    result = 31 * result + indicatorHorizontalPadding.hashCode()
    result = 31 * result + labelFontSize.hashCode()
    result = 31 * result + labelFontWeight.hashCode()
    result = 31 * result + labelColor.hashCode()
    result = 31 * result + selectedLabelColor.hashCode()
    result = 31 * result + animationDurationMillis.hashCode()
    result = 31 * result + easing.hashCode()
    result = 31 * result + indicatorRadius.hashCode()
    result = 31 * result + indicatorColor.hashCode()
    result = 31 * result + backgroundBrush.hashCode()
    return result
  }
}
