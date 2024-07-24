package ru.wildberries.ui.model

typealias DomainEventVisitor = ru.wb.domain.model.EventVisitor

internal data class EventVisitor(
    val visitorId: String,
    val imageUrl: String?
){
    companion object {
        val default = EventVisitor(
            visitorId = "",
            imageUrl = ""
        )
    }
}

internal fun DomainEventVisitor.toUiEventVisitor(): EventVisitor =
    EventVisitor(
        visitorId = this.visitorId,
        imageUrl = this.imageUrl
    )

internal fun EventVisitor.toDomainEventVisitor(): DomainEventVisitor =
    DomainEventVisitor(
        visitorId = this.visitorId,
        imageUrl = this.imageUrl
    )