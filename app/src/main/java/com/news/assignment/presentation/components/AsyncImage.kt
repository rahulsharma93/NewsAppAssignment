package com.news.assignment.presentation.components

import android.graphics.drawable.Drawable
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.news.assignment.presentation.util.conditional

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    url: String?,
    imageModifier: Modifier = Modifier,
    placeholder: Int? = null,
    contentScale: ContentScale = ContentScale.Fit,
    @RawRes animationResourceId: Int? = null,
    shouldFillMaxSize: Boolean = true,
    colorFilter: ColorFilter? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onSuccess: ((Drawable) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
) {

    val context = LocalContext.current
    var state: AsyncImagePainter.State by remember {
        mutableStateOf(AsyncImagePainter.State.Empty)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        coil.compose.AsyncImage(
            modifier = imageModifier
                .padding(contentPadding)
                .conditional(shouldFillMaxSize) {
                    fillMaxSize()
                },
            model = ImageRequest.Builder(context)
                .data(url)
                .placeholder(placeholder)
                .error(placeholder)
                .build(),
            contentDescription = null,
            onState = {
                state = it
                when (it) {
                    is AsyncImagePainter.State.Success -> onSuccess?.invoke(it.result.drawable)
                    is AsyncImagePainter.State.Error -> onError?.invoke(it.result.throwable)
                    else -> {}
                }
            },
            contentScale = contentScale,
            colorFilter = colorFilter,
        )
        if (state !is AsyncImagePainter.State.Success) {
            animationResourceId?.let {
                LottieAnimation(resourceId = it)
            }
        }
    }
}

private fun ImageRequest.Builder.error(drawableResId: Int?): ImageRequest.Builder {
    return drawableResId?.let {
        error(it)
    } ?: this
}

private fun ImageRequest.Builder.placeholder(drawableResId: Int?): ImageRequest.Builder {
    return drawableResId?.let {
        placeholder(it)
    } ?: this
}

@Composable
fun LottieAnimation(modifier: Modifier = Modifier, @RawRes resourceId: Int) {
    val rawComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resourceId))
    com.airbnb.lottie.compose.LottieAnimation(
        composition = rawComposition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier,
        contentScale = ContentScale.FillBounds,
    )
}
