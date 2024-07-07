package ru.wildberries.domain

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CommunityModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val amount: Int
){
    companion object {
        val default = CommunityModel(
            id = UUID.randomUUID().toString(),
            title = "Title",
            description = LoremIpsum(117).values.first(),
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            amount = 10000
        )
    }
}
