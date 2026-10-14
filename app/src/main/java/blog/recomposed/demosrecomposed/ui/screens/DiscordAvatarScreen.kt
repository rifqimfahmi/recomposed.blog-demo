package blog.recomposed.demosrecomposed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import blog.recomposed.demosrecomposed.ui.theme.Gray400
import blog.recomposed.demosrecomposed.ui.theme.Gray500
import blog.recomposed.demosrecomposed.ui.theme.Gray700
import blog.recomposed.demosrecomposed.ui.theme.Green700
import blog.recomposed.demosrecomposed.ui.theme.Red700
import blog.recomposed.demosrecomposed.ui.theme.Yellow600
import blog.recomposed.demosrecomposed.ui.theme.Zinc800
import coil3.compose.AsyncImage

private val StatusIndicatorOuterRadius = 24.dp
private val StatusIndicatorInnerRadius = 16.dp

sealed class Status(val color: Color) {
    data object Online: Status(color = Green700)
    data object Away: Status(color = Yellow600)
    data object Offline: Status(color = Gray400)
}

@Composable
fun DiscordAvatarScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Zinc800)
                .size(280.dp),
            contentAlignment = Alignment.Center
        ) {
            DiscordAvatar1(
                status = Status.Online
            )
        }
    }
}

@Composable
fun DiscordAvatar1(
    status: Status
) {
    Box(
        modifier = Modifier
            .size(128.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithCache {
                    // Paths only depend on `size`, so drawWithCache builds them once per
                    // size change instead of on every frame.
                    val fullRect = Path().apply {
                        addRect(Rect(
                            topLeft = Offset(0f, 0f),
                            bottomRight = Offset(size.width, size.height)
                        ))
                    }

                    // The circle the square image gets cropped to.
                    val innerCircle = Path().apply {
                        addOval(Rect(
                            center = Offset(size.width / 2f, size.height / 2f),
                            radius = size.minDimension / 2
                        ))
                    }

                    // XOR of the full square and the inner circle leaves just the four
                    // corners outside the circle, which get cleared below to round the image.
                    val cornerPath = Path.combine(
                        operation = PathOperation.Xor,
                        path1 = fullRect,
                        path2 = innerCircle
                    )

                    // Bottom-right corner, where the status badge sits.
                    val indicatorCenterOffset = Offset(
                        x = size.width - StatusIndicatorOuterRadius.toPx(),
                        y = size.height - StatusIndicatorOuterRadius.toPx()
                    )
                    // Punches a hole in the avatar so the badge isn't drawn over the photo.
                    val indicatorCutoutCircle = Path().apply {
                        addOval(Rect(
                            center = indicatorCenterOffset,
                            radius = StatusIndicatorOuterRadius.toPx()
                        ))
                    }

                    // The visible status badge, drawn inside the cutout hole.
                    val indicatorDotCircle = Path().apply {
                        addOval(Rect(
                            center = indicatorCenterOffset,
                            radius = StatusIndicatorInnerRadius.toPx()
                        ))
                    }

                    onDrawWithContent {
                        drawContent()

                        // BlendMode.Clear erases pixels, so the color passed here is ignored.
                        drawPath(
                            path = cornerPath,
                            color = Color.Transparent,
                            blendMode = BlendMode.Clear
                        )

                        drawPath(
                            path = indicatorCutoutCircle,
                            color = Color.Transparent,
                            blendMode = BlendMode.Clear
                        )

                        drawPath(
                            path = indicatorDotCircle,
                            color = status.color
                        )
                    }
                },
            model = "https://i.postimg.cc/CxC3QMhy/didin-hasbullah-KD2Cre3V19I-unsplash-2.jpg",
            contentDescription = "User profile photo",
            contentScale = ContentScale.Crop,
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
fun previewComponent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DiscordAvatarScreen()
    }
}