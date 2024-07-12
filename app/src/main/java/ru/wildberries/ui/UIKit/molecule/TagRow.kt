package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.wildberries.ui.UIKit.atom.Tag

@Composable
fun TagRow(
    tagList: List<String>
) {
    Row() {
        tagList.forEach{ tag ->
            Tag(text = tag)
        }
    }
}

@Preview
@Composable
private fun TagRowPreview() {
    TagRow(tagList = listOf("Moscow", "Python", "Junior"))
}