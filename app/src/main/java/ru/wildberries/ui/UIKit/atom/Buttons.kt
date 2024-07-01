package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.wildberries.ui.theme.WBTheme

@Immutable
object DisabledRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0f)
}

@Composable
fun PrimaryButton(
    content: @Composable () -> Unit = { DefaultButtonContent() },
    onClick: ()->Unit,
    isEnabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    CompositionLocalProvider(LocalRippleTheme provides DisabledRippleTheme) {
        Button(
            modifier = Modifier
                .alpha(if (isEnabled) 1f else 0.5f),
            enabled = isEnabled,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = WBTheme.colors.NeutralOffWhite,
                containerColor = when {
                    isPressed -> WBTheme.colors.BrandColorDark
                    else -> WBTheme.colors.BrandColorDefault
                },
                disabledContentColor = WBTheme.colors.NeutralOffWhite,
                disabledContainerColor = WBTheme.colors.BrandColorDefault
            ),
            interactionSource = interactionSource
        ) {
            content()
        }
    }
}

@Composable
fun SecondaryButton(
    content: @Composable () -> Unit = { DefaultButtonContent() },
    onClick: ()->Unit,
    isEnabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    CompositionLocalProvider(LocalRippleTheme provides DisabledRippleTheme) {

        OutlinedButton(
            modifier = Modifier
                .alpha(if (isEnabled) 1f else 0.5f),
            enabled = isEnabled,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = WBTheme.colors.NeutralWhite,
                contentColor = when {
                    isPressed -> WBTheme.colors.BrandColorDark
                    else -> WBTheme.colors.BrandColorDefault
                },
                disabledContainerColor = WBTheme.colors.NeutralWhite,
                disabledContentColor = WBTheme.colors.BrandColorDefault
            ),
            border = BorderStroke(
                1.dp,
                color = when {
                    isPressed -> WBTheme.colors.BrandColorDark
                    else -> WBTheme.colors.BrandColorDefault
                }
            ),
            interactionSource = interactionSource
        ) {
            content()
        }
    }
}
@Composable
fun GhostButton(
    content: @Composable () -> Unit = { DefaultButtonContent() },
    onClick: ()->Unit,
    isEnabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    CompositionLocalProvider(LocalRippleTheme provides DisabledRippleTheme) {

        TextButton(
            modifier = Modifier
                .alpha(if (isEnabled) 1f else 0.5f),
            enabled = isEnabled,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = WBTheme.colors.NeutralWhite,
                contentColor = when {
                    isPressed -> WBTheme.colors.BrandColorDark
                    else -> WBTheme.colors.BrandColorDefault
                },
                disabledContainerColor = WBTheme.colors.NeutralWhite,
                disabledContentColor = WBTheme.colors.BrandColorDefault
            ),
            interactionSource = interactionSource
        ) {
            content()
        }
    }
}

@Composable
fun DefaultButtonContent() {
    Text(
        text = "text",
        style = WBTheme.typography.subHeading2
    )
}
