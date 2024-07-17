package ru.wildberries.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object AuthBase : Route()

    @Serializable
    data object ProfileBase : Route()

    @Serializable
    data object EventBase: Route()

    @Serializable
    data object CommunityBase: Route()

    @Serializable
    data object LessonBase: Route()

    @Serializable
    data object Profile : Route()

    @Serializable
    data object More : Route()

    @Serializable
    data object FirstLesson : Route()

    @Serializable
    data object SecondLesson : Route()

    @Serializable
    data object PersonalEventList : Route()

    @Serializable
    data object EventList : Route()

    @Serializable
    data object CommunityList : Route()

    @Serializable
    data object Splash : Route()

    @Serializable
    data object VerificationPhoneNumber : Route()

    @Serializable
    data object VerificationPinCode : Route()

    @Serializable
    data object ProfileCreate : Route()
}

