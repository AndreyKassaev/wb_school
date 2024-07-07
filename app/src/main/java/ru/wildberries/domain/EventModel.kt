package ru.wildberries.domain

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class EventModel(
    val id: String,
    val communityId: String,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String,
    val location: String,
    val isActive: Boolean,
    val tagList: List<String>
){
    companion object {
        val default = EventModel(
            id = UUID.randomUUID().toString(),
            communityId = UUID.randomUUID().toString(),
            title = "Title",
            description = LoremIpsum(117).values.first(),
            date = "01.01.1970",
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            location = "Moscow",
            isActive = true,
            tagList = listOf("Moscow")
        )
    }
}
