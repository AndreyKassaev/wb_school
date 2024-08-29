package ru.wildberries.navigation

sealed class Router(
    val route: String
) {

    data object Base: Router("auth"){

        data object Auth : Router("auth_base")

        data object Profile : Router("profile_base")

        data object Event: Router("event_base")

        data object Community: Router("community_base")

        data object Lesson: Router("lesson_base")

    }

    data object MainScreen : Router("main_screen")

    data object ProfileEditScreen : Router("profile_edit")

    object CameraScreen : Router("camera")

    object PhotoScreen : Router("photo/{photo}") {
        fun withArgs(photo: String): String = "photo/$photo"
    }

    data object Profile : Router("profile")

    data object More : Router("more")

    data object FirstLesson : Router("first_lesson")

    data object SecondLesson : Router("second_lesson")

    data object PersonalEventList : Router("personal_event_list")

    data object EventList : Router("event_list")

    data object Event: Router("event/{event_id}"){
        fun withArg(eventId: String): String = "event/$eventId"
    }

    data object CommunityList : Router("community_list")

    data object Community: Router("community/{community_id}"){
        fun withArg(communityId: String): String = "community/$communityId"
    }

    data object Splash : Router("splash")

    data object VerificationPhoneNumber : Router("verification_phone_number")

    data object VerificationPinCode : Router("verification_pin_code/{phone_number}"){
        fun withArg(phoneNumber: String): String = "verification_pin_code/$phoneNumber"
    }

    data object ProfileCreate : Router("profile_create/{phone_number}"){
        fun withArg(phoneNumber: String): String = "profile_create/$phoneNumber"
    }

}

