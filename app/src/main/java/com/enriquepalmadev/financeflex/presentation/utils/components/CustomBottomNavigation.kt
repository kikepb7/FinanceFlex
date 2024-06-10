package com.enriquepalmadev.financeflex.presentation.utils.components


import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enriquepalmadev.financeflex.R
import kotlin.math.PI
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMAtrix = RenderEffect
        .createColorFilterEffect(
            ColorMatrixColorFilter(
                ColorMatrix(
                    floatArrayOf(
                        1f, 0f, 0f, 0f, 0f,
                        0f, 1f, 0f, 0f, 0f,
                        0f, 0f, 1f, 0f, 0f,
                        0f, 0f, 0f, 50f, -5000f
                    )
                )
            )
        )
    return RenderEffect
        .createChainEffect(alphaMAtrix, blurEffect)
}

@Composable
fun Screen() {
    val isMenuExtended = remember { mutableStateOf(false) }
    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ),
        label = ""
    )
    
    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        ),
        label = ""
    )
    
    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    Screen(
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}

@Composable
fun Screen(
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    togleAnimation: () -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomBottomNavigation()
        Circle(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )
        FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress)
        FabGroup(
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = togleAnimation
        )
        Circle(
            color = Color.White,
            animationProgress = clickAnimationProgress
        )
    }
}

@Composable
fun Circle(
    color: Color,
    animationProgress: Float
) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(44.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = color.copy(alpha = color.alpha * animationValue),
                shape = CircleShape
            )
    )
}

@Composable
fun CustomBottomNavigation() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(80.dp)
            .paint(
                painter = painterResource(id = R.drawable.bottom_navigation),
                contentScale = ContentScale.FillHeight
            )
            .padding(horizontal = 40.dp)
    ) {
        listOf(
            Icons.Rounded.Home,
            Icons.Rounded.AccountCircle
        ).map { image ->
            IconButton(onClick = { }) {
                Icon(
                    imageVector = image,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = 44.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        AnimatedFab(
            icon = Icons.Rounded.Notifications,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp * FastOutSlowInEasing.transform(animationProgress),
                        end = 210.dp * FastOutSlowInEasing.transform(animationProgress)
                    )
                )
        )

        AnimatedFab(
            icon = Icons.Default.Settings,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 88.dp * FastOutSlowInEasing.transform(animationProgress),
                )
            ),
        )

        AnimatedFab(
            icon = Icons.Rounded.Wallet,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 72.dp * FastOutSlowInEasing.transform(animationProgress),
                    start = 210.dp * FastOutSlowInEasing.transform(animationProgress)
                )
            )
        )

        AnimatedFab(
            modifier = Modifier
                .scale(1f - FastOutSlowInEasing.transform(animationProgress)),
        )

        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing.transform(animationProgress)
                ),
            onClick = toggleAnimation,
        )
    }
}

/*@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toogleAnimation: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 44.dp)
            .graphicsLayer { this.renderEffect = renderEffect },
        contentAlignment = Alignment.BottomCenter
    ) {

        AnimatedFab(
            icon = Icons.Default.PhotoCamera,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp * FastOutSlowInEasing.transform(animationProgress),
                        end = 210.dp * FastOutSlowInEasing.transform(animationProgress)
                    )
                )
                .graphicsLayer { alpha = LinearEasing.transform(animationProgress) },
        )

        AnimatedFab(
            icon = Icons.Default.Settings,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 88.dp * FastOutSlowInEasing.transform(animationProgress)
                    )
                )
                .graphicsLayer { alpha = LinearEasing.transform(animationProgress) },
        )

        AnimatedFab(
            icon = Icons.Default.ShoppingCart,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp * FastOutSlowInEasing.transform(animationProgress),
                        end = 210.dp * FastOutSlowInEasing.transform(animationProgress)
                    )
                )
                .graphicsLayer { alpha = LinearEasing.transform(animationProgress) },
        )

        AnimatedFab(
            modifier = Modifier
                .scale(1f - LinearEasing.transform(animationProgress))
        )

        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing
                        .transform(animationProgress)
                ),
            onClick = { toogleAnimation() }
        )
    }
}*/

@Composable
fun AnimatedFab(
    icon: ImageVector? = null,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = { onClick() },
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        contentColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .scale(1.25f),
        shape = CircleShape
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = 1f)
            )
        }
    }
}

@Preview(device = "id:pixel_5", showBackground = true)
@Composable
fun CustomBottomNavigationBarPreview() {
    Screen()
}
