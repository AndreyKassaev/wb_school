package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import ru.wildberries.ui.theme.WBTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        modifier = Modifier
            .alpha(if (isEnabled) 1f else 0.5f)
            .then(modifier),
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

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    OutlinedButton(
        modifier = Modifier
            .alpha(if (isEnabled) 1f else 0.5f)
            .then(modifier),
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

@Composable
fun GhostButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    TextButton(
        modifier = Modifier
            .alpha(if (isEnabled) 1f else 0.5f)
            .then(modifier),
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
