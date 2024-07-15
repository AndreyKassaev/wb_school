package ru.wildberries.util

fun Any.classToRoute(): String =
    this::class.qualifiedName?.substringBefore(".Companion").toString()
