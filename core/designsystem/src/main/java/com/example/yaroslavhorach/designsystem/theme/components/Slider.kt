package com.example.yaroslavhorach.designsystem.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun PomoSlider(
    steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float>,
    value: Float,
    tooltip: String,
    onValueChangeFinished: ((Float) -> Unit)
) {
    val path = remember { Path() }
    val textMeasure = rememberTextMeasurer()
    val spanStyle = remember {
        SpanStyle(
            color = Color.White,
            fontSize = 8.sp,
        )
    }
    val tooltipColor = MaterialTheme.colorScheme.primary
    val thumbSize = 20.dp
    val tooltipText = buildAnnotatedString {
        withStyle(style = spanStyle) {
            append(tooltip)
        }
    }
    val textLayoutResult = textMeasure.measure(text = tooltipText)

    Slider(
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(inactiveTrackColor = MaterialTheme.colorScheme.onBackground),
        steps = steps,
        valueRange = valueRange,
        value = value,
        onValueChange = onValueChangeFinished,
        thumb = {
            SliderDefaults.Thumb(
                interactionSource = remember { MutableInteractionSource() },
                modifier = Modifier.drawBehind {
                    val tooltipWidth = textLayoutResult.size.width.toFloat()
                    val tooltipHeight = 25.dp.toPx()
                    val tooltipBottomPadding = 2.dp.toPx()

                    val tooltipLeft = -(tooltipWidth / 2) + thumbSize.toPx() / 2
                    val tooltipRight = tooltipWidth / 2 + thumbSize.toPx() / 2
                    val tooltipTextHorizontalPadding = 4.dp.toPx()

                    drawPath(
                        path.apply {
                            reset()
                            addRoundRect(
                                RoundRect(
                                    left = tooltipLeft - tooltipTextHorizontalPadding,
                                    top = -tooltipHeight,
                                    right = tooltipRight + tooltipTextHorizontalPadding,
                                    bottom = -tooltipBottomPadding,
                                    cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx())
                                )
                            )
                        },
                        color = tooltipColor
                    )

                    drawText(
                        textMeasurer = textMeasure,
                        text = tooltipText,
                        topLeft = Offset(tooltipLeft, -(tooltipHeight / 1.25f)),
                        size = Size(tooltipWidth, tooltipHeight)
                    )
                },
                colors = SliderDefaults.colors(inactiveTrackColor = MaterialTheme.colorScheme.onBackground),
                enabled = true
            )
        }
    )
}