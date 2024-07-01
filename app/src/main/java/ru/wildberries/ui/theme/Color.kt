package ru.wildberries.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Immutable
data class WBColors(
    val BrandColorDark: Color, //use for on pressed
    val BrandColorDefault: Color, //use for button
    val BrandColorDarkMode: Color, //use for Dark Mode
    val BrandColorLight: Color, //variant
    val BrandColorBG: Color, //use for background
    val NeutralActive: Color, //use for font
    val NeutralDark: Color, //use for dark mode
    val NeutralBody: Color, //use for text
    val NeutralWeak: Color, //use for use for secondary text
    val NeutralDisabled: Color, //use for disabled
    val NeutralLine: Color, //use for divider
    val NeutralOffWhite: Color, //use for BG (aka Neutral/Off-white)
    val NeutralWhite: Color, //use for BG
    val AccentDanger: Color, //use for errors
    val AccentWarning: Color, //use for warning
    val AccentSuccess: Color, //use for success
    val AccentSafe: Color, //use for variant
    val Gradient01: Brush,
    val Gradient02: Brush,
)

val LocalWBColors = staticCompositionLocalOf {
    WBColors(
        BrandColorDark = Color.Unspecified,
        BrandColorDefault = Color.Unspecified,
        BrandColorDarkMode = Color.Unspecified,
        BrandColorLight = Color.Unspecified,
        BrandColorBG = Color.Unspecified,
        NeutralActive = Color.Unspecified,
        NeutralDark = Color.Unspecified,
        NeutralBody = Color.Unspecified,
        NeutralWeak = Color.Unspecified,
        NeutralDisabled = Color.Unspecified,
        NeutralLine = Color.Unspecified,
        NeutralOffWhite = Color.Unspecified,
        NeutralWhite = Color.Unspecified,
        AccentDanger = Color.Unspecified,
        AccentWarning = Color.Unspecified,
        AccentSuccess = Color.Unspecified,
        AccentSafe = Color.Unspecified,
        Gradient01 = Brush.linearGradient(
            listOf(
                Color.Unspecified,
                Color.Unspecified
            )
        ),
        Gradient02 = Brush.linearGradient(
            listOf(
                Color.Unspecified,
                Color.Unspecified
            )
        )
    )
}