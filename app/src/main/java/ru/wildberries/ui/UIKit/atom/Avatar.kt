package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.wildberries.R

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    img: Int
) {
    Image(
        modifier = Modifier
            .size(48.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .composed {
                modifier
            }
        ,
        painter = painterResource(id = img),
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop
    )
}