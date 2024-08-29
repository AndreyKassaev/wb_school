package ru.wildberries.ui.screen.profile.edit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import java.io.File
import java.io.FileInputStream

@Composable
internal fun PhotoPreviewScreen(
    photo: String?,
    viewModel: ProfileEditViewModel = koinViewModel()
) {

    val navController = LocalNavController.current
    val context = LocalContext.current
    var scale by remember {
        mutableStateOf(1f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
            scale = (scale * zoomChange).coerceIn(1f, 5f)

            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight =
                (scale - 1) * constraints.maxHeight + (constraints.maxHeight - constraints.maxWidth)

            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            offset = Offset(
                x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY),
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("${context.filesDir}/$photo")
                .build(),
            contentDescription = "icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state)
                .clipToBounds()
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                // ONLY ADD THIS
                .graphicsLayer {
                    alpha = .99f
                }
        ) {
            with(drawContext.canvas.nativeCanvas) {
                val checkPoint = saveLayer(null, null)

                drawRect(Color.Black.copy(alpha = 0.8f))

                drawCircle(
                    color = Color.Transparent,
                    blendMode = BlendMode.Clear
                )
                restoreToCount(checkPoint)
            }
        }
        val file = File(context.filesDir, photo)
        val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
        val gradient = Brush.linearGradient(listOf(Color(0xFFED3CCA), Color(0xFF6600FF)))

        Button(
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 28.dp),
            onClick = {
                try {
                    val croppedImage = cropImage(
                        targetBitmap = bitmap,
                        cropSize = Size(
                            width = constraints.maxWidth.toFloat(),
                            height = constraints.maxWidth.toFloat()
                        ),
                        offset = offset,
                        scale = scale
                    )

                    val filename = "${System.currentTimeMillis()}.jpg"

                    croppedImage?.let { btm ->
                        context.openFileOutput(filename, Context.MODE_PRIVATE).use { output ->
                            btm.compress(Bitmap.CompressFormat.JPEG, 100, output)
                        }
                    }

                    viewModel.saveProfileImage(imageUrl = filename)

                    navController.navigate(Router.ProfileEditScreen.route) {
                        popUpTo(Router.ProfileEditScreen.route) {
                            inclusive = true
                        }
                    }
                } catch (e: Exception) {

                }
            }
        ) {
            Box(
                modifier = Modifier
                    .background(gradient)
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Save",
                    fontSize = 24.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

private fun cropImage(
    targetBitmap: Bitmap,
    cropSize: Size,
    offset: Offset,
    scale: Float
): Bitmap {

    val matrix = Matrix().apply {
        setScale(
            scale,
            scale
        )
    }

    val scaledBitmap = Bitmap
        .createBitmap(
            targetBitmap,
            0,
            0,
            targetBitmap.width,
            targetBitmap.height,
            matrix,
            false
        )

    val cropX = scaledBitmap.width.minus(cropSize.width).div(2)
        .minus(offset.x).toInt()

    val cropY = scaledBitmap.height.minus(cropSize.height).div(2)
        .minus(offset.y).toInt()

    val croppedBitmap = Bitmap.createBitmap(
        scaledBitmap,
        cropX,
        cropY,
        cropSize.width.toInt(),
        cropSize.height.toInt(),
    )

    return croppedBitmap
}