package com.news.assignment.presentation.util
import androidx.compose.ui.Modifier

fun Modifier.conditional(
    enable: Boolean,
    conditionalModifier: Modifier.() -> Modifier,
): Modifier {
    return if (enable) {
        conditionalModifier()
    } else {
        this
    }
}