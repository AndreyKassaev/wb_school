package ru.wildberries.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ru.wildberries.ui.screen.MainScreen
import ru.wildberries.ui.screen.profile.create.ProfileCreateScreen
import ru.wildberries.ui.screen.auth.phone.PhoneNumberScreen
import ru.wildberries.ui.screen.auth.pincode.PinCodeScreen
import ru.wildberries.ui.screen.community.detail.CommunityDetailScreen
import ru.wildberries.ui.screen.community.list.CommunityListScreen
import ru.wildberries.ui.screen.event.detail.EventDetailScreen
import ru.wildberries.ui.screen.event.list.EventListScreen
import ru.wildberries.ui.screen.event.personal.PersonalEventListScreen
import ru.wildberries.ui.screen.lesson.FirstLessonScreen
import ru.wildberries.ui.screen.lesson.SecondLessonScreen
import ru.wildberries.ui.screen.more.MoreScreen
import ru.wildberries.ui.screen.profile.edit.ProfileEditScreen
import ru.wildberries.ui.screen.profile.detail.ProfileDetailScreen
import ru.wildberries.ui.screen.profile.edit.CameraScreen
import ru.wildberries.ui.screen.profile.edit.PhotoPreviewScreen
import ru.wildberries.ui.screen.splash.SplashScreen

val LocalNavController = compositionLocalOf<NavController> {
    error("No NavController found!")
}
val LocalAuthController = compositionLocalOf<AuthController> {
    error("No AuthController found!")
}

data class AuthController(
    val permission: String
)

@Composable
fun Navigation() {

    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        CompositionLocalProvider(
            LocalAuthController provides AuthController("")
        ) {
            Scaffold(
                bottomBar = {
//                    BottomBar()
                }
            ) { innerPadding ->
                NavHost(
                    modifier = Modifier
                        .padding(innerPadding),
                    navController = navController,
                    startDestination = Router.ProfileEditScreen.route,
                ) {
                    navigation(
                        route = Router.Base.Auth.route,
                        startDestination = Router.VerificationPhoneNumber.route,
                    ) {
                        composable(
                            route = Router.VerificationPhoneNumber.route
                        ) {
                            PhoneNumberScreen()
                        }
                        composable(
                            route = Router.VerificationPinCode.route
                        ) {
                            PinCodeScreen()
                        }
                        composable(
                            route = Router.ProfileCreate.route
                        ) {
                            ProfileCreateScreen()
                        }
                    }
                    navigation(
                        route = Router.Base.Event.route,
                        startDestination = Router.EventList.route
                    ) {
                        composable(
                            route = Router.EventList.route
                        ) {
                            EventListScreen()
                        }
                        composable(
                            route = Router.Event.route
                        ) {
                            EventDetailScreen()
                        }
                    }
                    navigation(
                        route = Router.Base.Community.route,
                        startDestination = Router.CommunityList.route
                    ) {
                        composable(
                            route = Router.CommunityList.route
                        ) {
                            CommunityListScreen()
                        }
                        composable(
                            route = Router.Community.route
                        ) {
                            CommunityDetailScreen()
                        }
                    }
                    navigation(
                        route = Router.Base.Profile.route,
                        startDestination = Router.More.route
                    ) {
                        composable(
                            route = Router.Profile.route
                        ) {
                            ProfileDetailScreen()
                        }
                        composable(
                            route = Router.More.route
                        ) {
                            MoreScreen()
                        }
                        composable(
                            route = Router.PersonalEventList.route
                        ) {
                            PersonalEventListScreen()
                        }
                    }
                    navigation(
                        route = Router.Base.Lesson.route,
                        startDestination = Router.FirstLesson.route
                    ) {
                        composable(
                            route = Router.FirstLesson.route
                        ) {
                            FirstLessonScreen()
                        }
                        composable(
                            route = Router.SecondLesson.route
                        ) {
                            SecondLessonScreen()
                        }
                    }
                    dialog(
                        route = Router.Splash.route,
                        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
                    ) {
                        SplashScreen()
                    }
                    composable(
                        route = Router.MainScreen.route
                    ) {
                        MainScreen()
                    }
                    composable(
                        route = Router.ProfileEditScreen.route
                    ) {
                        ProfileEditScreen()
                    }
                    composable(route = Router.PhotoScreen.route) {
                        val photo = it.arguments?.getString("photo")
                        PhotoPreviewScreen(photo = photo)
                    }
                    composable(route = Router.CameraScreen.route) {
                        CameraScreen()
                    }
                }
            }
        }
    }
}