package ru.wildberries.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SchoolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun WBTheme(
    content: @Composable () -> Unit
) {
    val WBColors = WBColors(
        BrandColorDark = Color(0xFF660EC8),
        BrandColorDefault = Color(0xFF9A41FE),
        BrandColorDarkMode = Color(0xFF8207E8),
        BrandColorLight = Color(0xFFECDAFF),
        BrandColorBG = Color(0xFFF5ECFF),
        NeutralActive = Color(0xFF29183B),
        NeutralDark = Color(0xFF190E26),
        NeutralBody = Color(0xFF1D0835),
        NeutralWeak = Color(0xFFA4A4A4),
        NeutralDisabled = Color(0xFFADB5BD),
        NeutralLine = Color(0xFFEDEDED),
        NeutralOffWhite = Color(0xFFF7F7FC),
        NeutralWhite = Color(0xFFFFFFFF),
        AccentDanger = Color(0xFFE94242),
        AccentWarning = Color(0xFFFDCF41),
        AccentSuccess = Color(0xFF2CC069),
        AccentSafe = Color(0xFF7BCBCF),
        Gradient01 = Brush.linearGradient(
            listOf(
                Color(0xFFD2D5F9),
                Color(0xFF2C37E1)
            )
        ),
        Gradient02 = Brush.linearGradient(
            listOf(
                Color(0xFFEC9EFF),
                Color(0xFF5F2EEA)
            )
        )
    )
    val WBTypography = WBTypography(
        heading1 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ),
        heading2 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        subHeading1 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        ),
        subHeading2 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        bodyText1 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        ),
        bodyText2 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        metadata1 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        metadata2 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp
        ),
        metadata3 = TextStyle(
            fontFamily = SFProDisplay,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp
        ),
    )
    CompositionLocalProvider(
        LocalWBColors provides WBColors,
        LocalWBTypography provides WBTypography,
        content = content
    )
}

object WBTheme {
    val colors: WBColors
        @Composable
        get() = LocalWBColors.current
    val typography: WBTypography
        @Composable
        get() = LocalWBTypography.current
}
