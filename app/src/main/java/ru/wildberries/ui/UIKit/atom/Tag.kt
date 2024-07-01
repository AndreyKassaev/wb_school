package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.ui.theme.WBTheme

@Composable
fun Tag(
    text: String
) {
    Row(
        modifier = Modifier
            .padding(end = 4.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(WBTheme.colors.BrandColorBG)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 1.dp, end = 8.dp, bottom = 1.dp),
            style = WBTheme.typography.metadata3,
            text = text,
            color = WBTheme.colors.BrandColorDark
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TagPreview() {
    WBTheme {
        Tag(text = "Python")
    }
}