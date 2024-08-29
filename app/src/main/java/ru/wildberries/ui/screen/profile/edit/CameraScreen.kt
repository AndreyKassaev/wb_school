package ru.wildberries.ui.screen.profile.edit

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router


@Composable
fun CameraScreen() {

    val gradient = Brush.linearGradient(listOf(Color(0xFFED3CCA), Color(0xFF6600FF)))
    val filename = "${System.currentTimeMillis()}.jpeg"
    val navController = LocalNavController.current
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraPreview(
            controller = controller,
            modifier = Modifier
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                modifier = Modifier.scale(2f),
                onClick = {
                    takePhoto(
                        controller = controller,
                        onPhotoTaken = {
                            navController.navigate(Router.PhotoScreen.withArgs(filename))
                        },
                        context = context,
                        filename = filename
                    )
                }
            ) {
                Icon(
                    modifier = Modifier
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        },
                    painter = painterResource(id = R.drawable.take_picture),
                    contentDescription = "take picture",
                )
            }
            IconButton(
                modifier = Modifier.scale(2f),
                onClick = {
                    controller.cameraSelector = cameraSwitch(controller)
                }
            ) {
                Icon(
                    modifier = Modifier
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        },
                    painter = painterResource(id = R.drawable.camera_change),
                    contentDescription = "camera change"
                )
            }
        }
    }
}

private fun cameraSwitch(controller: CameraController): CameraSelector =
    if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
        CameraSelector.DEFAULT_FRONT_CAMERA
    } else {
        CameraSelector.DEFAULT_BACK_CAMERA
    }


private fun takePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: () -> Unit,
    context: Context,
    filename: String
) {

    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postScale(-1F, 1F)
                }

                val bitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                context.openFileOutput(filename, MODE_PRIVATE).use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                }.also {
                    onPhotoTaken()
                }
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: ", exception)
            }
        }
    )
}