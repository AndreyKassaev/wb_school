package ru.wildberries.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Community(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val size: Int
){
    companion object {
        val default = Community(
            id = UUID.randomUUID().toString(),
            title = "Title",
            description = LoremIpsum(117).values.first(),
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            size = 10000
        )
    }
}
