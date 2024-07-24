package ru.wb.domain.model

data class EventVisitor(
    val visitorId: String,
    val imageUrl: String?
)

fun Profile.toEventVisitor(): EventVisitor =
    EventVisitor(
        visitorId = this.id,
        imageUrl = this.imageUrl
    )