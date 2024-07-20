package ru.wildberries.util

import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wildberries.navigation.Route

fun Any.classToRoute(): String =
    this::class.qualifiedName?.substringBefore(".Companion").toString()

fun Community.toRoute(): Route.Community =
    Route.Community(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        size = this.size
    )

fun Event.toRoute(): Route.Event =
    Route.Event(
        id = this.id,
        communityId = this.id,
        title = this.title,
        description = this.description,
        date = this.date,
        imageUrl = this.imageUrl,
        location = this.location,
        isActive = this.isActive,
        tagList = this.tagList
    )
