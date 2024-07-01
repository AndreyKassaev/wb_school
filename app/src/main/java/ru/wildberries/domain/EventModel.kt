package ru.wildberries.domain

import kotlinx.serialization.Serializable
import ru.wildberries.R

@Serializable
data class EventModel(
    val img: Int = R.drawable.avatar_meeting,
    val name: String = "Developer meeting",
    val date: String = "13.09.2024",
    val location: String = "Москва",
    val isActive: Boolean = true,
    val tagList: List<String> = listOf("Python", "Junior", "Moscow")
)
