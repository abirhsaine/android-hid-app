package com.example.redeemers_faz_com.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController



private val DarkColorPalette = darkColors(
    primary = Color(0xFFB006FC),
    primaryVariant = Color(0xFF0386FC),
    secondary = Color(0xFFB046FC)
)

private val LightColorPalette = lightColors(
    primary = Color(0xF30B86FC),
    primaryVariant = Color(0xF04B86FC),
    secondary = Color(0xF5CB86FC)
)

@Composable
fun NavComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    useSystemUiController: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if (useSystemUiController) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = colors.primaryVariant
        )
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}

private val Shapes = Shapes(
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(3.dp),
    large = RoundedCornerShape(0.dp)
)