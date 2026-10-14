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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import blog.recomposed.demosrecomposed.ui.theme.Gray400
import blog.recomposed.demosrecomposed.ui.theme.Gray50
import blog.recomposed.demosrecomposed.ui.theme.Gray500
import blog.recomposed.demosrecomposed.ui.theme.Gray700
import blog.recomposed.demosrecomposed.ui.theme.Green700
import blog.recomposed.demosrecomposed.ui.theme.Red400
import blog.recomposed.demosrecomposed.ui.theme.Red700
import blog.recomposed.demosrecomposed.ui.theme.Yellow600
import blog.recomposed.demosrecomposed.ui.theme.Zinc800
import coil3.compose.AsyncImage

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
                status = Status.Offline
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
                .drawWithContent {
                    drawContent()

                    val fullRect = Path().apply {
                        addRect(Rect(
                            topLeft = Offset(0f, 0f),
                            bottomRight = Offset(size.width, size.height)
                        ))
                    }

                    val innerCircle = Path().apply {
                        addOval(Rect(
                            center = center,
                            radius = size.minDimension / 2
                        ))
                    }

                    val cornerPath = Path.combine(
                        operation = PathOperation.Xor,
                        path1 = fullRect,
                        path2 = innerCircle
                    )
                    drawPath(
                        path = cornerPath,
                        color = Red400,
                        blendMode = BlendMode.Clear
                    )

                    val bigRadius = 24.dp
                    val smallRadius = 16.dp

                    val indicatorCenterOffset = Offset(
                        x = size.width - bigRadius.toPx(),
                        y = size.height - bigRadius.toPx()
                    )
                    val biggerCircle = Path().apply {
                        addOval(Rect(
                            center = indicatorCenterOffset,
                            radius = bigRadius.toPx()
                        ))
                    }

                    drawPath(
                        path = biggerCircle,
                        color = Gray50,
                        blendMode = BlendMode.Clear
                    )

                    val smallerCircle = Path().apply {
                        addOval(Rect(
                            center = indicatorCenterOffset,
                            radius = smallRadius.toPx()
                        ))
                    }

                    drawPath(
                        path = smallerCircle,
                        color = status.color
                    )
                },
            model = "https://i.postimg.cc/CxC3QMhy/didin-hasbullah-KD2Cre3V19I-unsplash-2.jpg",
            contentDescription = "User profile photo",
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