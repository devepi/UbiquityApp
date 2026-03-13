package com.example.ubiquiti.catalog.ui.shared.segments

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastZip
import com.example.ubiquiti.catalog.ui.shared.container.GlassSurfaceContainer

@Preview
@Composable
fun SegmentedControlPreview() {
  SegmentedControl(
    selectedIndex = 1,
    onItemSelected = { selected ->

    },
    items = listOf(
      SegmentedControlItem("All"),
      SegmentedControlItem("Cameras"),
      SegmentedControlItem("IOT"),
    ),
  )
}

@Composable
fun SegmentedControl(
  modifier: Modifier = Modifier,
  segmentedControlProperties: SegmentedControlProperties = SegmentedControlPropertiesDefault.values(),
  selectedIndex: Int,
  onItemSelected: (Int) -> Unit,
  items: List<SegmentedControlItem>,
) {
  val itemUis: @Composable () -> Unit = {
    items.mapIndexed { index, item ->
      SegmentedControlItemUi(
        item = item,
        properties = segmentedControlProperties,
        selected = selectedIndex == index,
        onClick = { onItemSelected(index) }
      )
    }
  }

  val indicator: @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit =
    segmentedControlIndicator(selectedIndex, segmentedControlProperties)

  Row(modifier) {
    SegmentedControlContainer(
      items = itemUis,
      indicator = indicator,
    )
  }
}

@Composable
private fun SegmentedControlContainer(
  modifier: Modifier = Modifier,
  segmentedControlProperties: SegmentedControlProperties = SegmentedControlPropertiesDefault.values(),
  items: @Composable () -> Unit,
  indicator: @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit,
) {
  GlassSurfaceContainer(
    modifier.defaultMinSize(44.dp)
  ) {
    SubcomposeLayout(
      Modifier
        .padding(horizontal = segmentedControlProperties.containerPadding)
    ) { constraints ->

      val itemMeasurables = subcompose(ItemsSlot, items)

      val containerHeight = itemMeasurables.maxOf { it.maxIntrinsicHeight(Constraints.Infinity) }
      val labelWidths = itemMeasurables.fastMap { it.maxIntrinsicWidth(containerHeight) }

      val remainingWidth = constraints.maxWidth - labelWidths.sum()
      val space = remainingWidth / (labelWidths.size * 2)
      val itemWidths = labelWidths.fastMap { textWidth ->
        textWidth + space * 2
      }
      val itemTotalWidth = itemWidths.sum()

      val itemPlaceable = itemMeasurables.fastZip(itemWidths) { measurable, itemWidth ->
        measurable.measure(
          Constraints.fixed(itemWidth, containerHeight)
        )
      }

      val lefts = itemWidths.runningFold(0) { acc, w -> acc + w }.dropLast(1)

      val indicatorPositions = itemPlaceable.fastZip(lefts) { item, left ->
        IndicatorPosition(left.toDp(), item.width.toDp())
      }

      layout(itemTotalWidth, containerHeight) {
        subcompose(IndicatorSlot) {
          indicator(indicatorPositions)
        }.fastMap {
          it.measure(Constraints.fixed(itemTotalWidth, containerHeight)).place(0, 0)
        }

        itemPlaceable.fastZip(lefts) { itemPlaceable, left ->
          itemPlaceable.place(left, 0)
        }
      }
    }
  }
}

internal fun Modifier.indicatorOffset(
  indicatorPosition: IndicatorPosition,
  animationDurationMillis: Int,
  easing: Easing
): Modifier =
  composed {
    val indicatorWidth by animateDpAsState(
      indicatorPosition.width,
      animationSpec = tween(durationMillis = animationDurationMillis, easing = easing)
    )
    val indicatorOffset by animateDpAsState(
      indicatorPosition.left,
      animationSpec = tween(durationMillis = animationDurationMillis, easing = easing)
    )
    fillMaxWidth()
      .wrapContentSize(Alignment.BottomStart)
      .offset(x = indicatorOffset)
      .width(indicatorWidth)
  }


internal object ItemsSlot
internal object IndicatorSlot

@Immutable
internal data class IndicatorPosition(
  val left: Dp,
  val width: Dp
)

@Composable
internal fun segmentedControlIndicator(
  selectedIndex: Int,
  segmentedControlProperties: SegmentedControlProperties
): @Composable (indicatorPositions: List<IndicatorPosition>) -> Unit = {
  Box(
    modifier = Modifier
      .indicatorOffset(
        it[selectedIndex],
        animationDurationMillis = segmentedControlProperties.animationDurationMillis,
        easing = segmentedControlProperties.easing
      )
      .fillMaxSize()
      .background(
        shape = RoundedCornerShape(segmentedControlProperties.indicatorRadius),
        brush = segmentedControlProperties.backgroundBrush,
      )
  )
}
