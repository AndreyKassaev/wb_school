package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import ru.wildberries.ui.UIKit.atom.Tag

@Composable
fun TagsRow(
    tagList: List<String> = listOf(
        "Python",
        "Junior",
        "Moscow"
    )
) {
    Row() {
        tagList.forEach{ tag ->
            Tag(text = tag)
        }
    }
}
