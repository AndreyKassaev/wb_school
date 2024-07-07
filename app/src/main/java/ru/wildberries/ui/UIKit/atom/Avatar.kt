package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ru.wildberries.R

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(coil.size.Size.ORIGINAL) //necessary to specify the size, otherwise the state is always loading
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop //necessary to determine the correct dimensions to load the image at.
    )
    when (painter.state) {
        is AsyncImagePainter.State.Loading -> {
            CircularProgressIndicator()
        }
        is AsyncImagePainter.State.Error, AsyncImagePainter.State.Empty -> {
            Text(text = "Oops...")
        }
        is AsyncImagePainter.State.Success -> {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .composed {
                        modifier
                    }
                ,
                painter = painter,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop
            )
        }
    }
}