package ru.wildberries.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.domain.model.Community
import ru.wildberries.domain.model.Event
import ru.wildberries.ui.UIKit.organism.BottomBar
import ru.wildberries.ui.screen.auth.AuthViewModel
import ru.wildberries.ui.screen.auth.ProfileCreateScreen
import ru.wildberries.ui.screen.auth.VerificationPhoneNumberScreen
import ru.wildberries.ui.screen.auth.VerificationPinCodeScreen
import ru.wildberries.ui.screen.community.CommunityDetailScreen
import ru.wildberries.ui.screen.community.CommunityListScreen
import ru.wildberries.ui.screen.community.CommunityViewModel
import ru.wildberries.ui.screen.event.EventDetailScreen
import ru.wildberries.ui.screen.event.EventListScreen
import ru.wildberries.ui.screen.event.EventViewModel
import ru.wildberries.ui.screen.event.PersonalEventListScreen
import ru.wildberries.ui.screen.lesson.FirstLessonScreen
import ru.wildberries.ui.screen.lesson.LessonViewModel
import ru.wildberries.ui.screen.lesson.SecondLessonScreen
import ru.wildberries.ui.screen.profile.MoreScreen
import ru.wildberries.ui.screen.profile.ProfileScreen
import ru.wildberries.ui.screen.profile.ProfileViewModel
import ru.wildberries.ui.screen.splash.SplashScreen
import ru.wildberries.ui.screen.splash.SplashViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = SplashRoute,
        ) {
            navigation<VerificationRoute>(
                startDestination = VerificationPhoneNumberRoute,
            ){
                composable<VerificationPhoneNumberRoute> {
                    VerificationPhoneNumberScreen(
                        viewModel = koinViewModel<AuthViewModel>(),
                        navController = navController
                    )
                }
                composable<VerificationPinCodeRoute> {
                    VerificationPinCodeScreen(
                        viewModel = koinViewModel<AuthViewModel>(),
                        navController = navController
                    )
                }
                composable<ProfileCreateRoute> {
                    ProfileCreateScreen(
                        viewModel = koinViewModel<AuthViewModel>(),
                        navController = navController
                    )
                }
            }
            navigation<EventRoute>(
                startDestination = EventListRoute
            ){
                composable<EventListRoute> {
                    EventListScreen(
                        viewModel = koinViewModel<EventViewModel>(),
                        navController = navController
                    )
                }
                composable<Event> { backStackEntry ->
                    val event: Event = backStackEntry.toRoute()
                    EventDetailScreen(
                        viewModel = koinViewModel<EventViewModel>(),
                        event = event,
                        navController = navController
                    )
                }
            }
            navigation<CommunityRoute>(
                startDestination = CommunityListRoute
            ){
                composable<CommunityListRoute> {
                    CommunityListScreen(
                        viewModel = koinViewModel<CommunityViewModel>(),
                        navController = navController
                    )
                }
                composable<Community> { backStackEntry ->
                    val community: Community = backStackEntry.toRoute()
                    CommunityDetailScreen(
                        viewModel = koinViewModel<CommunityViewModel>(),
                        community = community,
                        navController = navController
                    )
                }
            }
            navigation<ProfileBaseRoute>(
                startDestination = MoreRoute
            ){
                composable<ProfileRoute> { backStackEntry ->
                    ProfileScreen(
                        viewModel = koinViewModel<ProfileViewModel>(),
                        navController = navController
                    )
                }
                composable<MoreRoute> { navBackStackEntry ->
                    MoreScreen(
                        viewModel = koinViewModel<ProfileViewModel>(),
                        navController = navController
                    )
                }
                composable<PersonalEventListRoute> {
                    PersonalEventListScreen(
                        viewModel = koinViewModel<EventViewModel>(),
                        navController = navController
                    )
                }
            }
            navigation<LessonRoute>(
                startDestination = FirstLessonRoute
            ){
                composable<FirstLessonRoute> {
                    FirstLessonScreen(
                        viewModel = koinViewModel<LessonViewModel>(),
                    )
                }
                composable<SecondLessonRoute> {
                    SecondLessonScreen(
                        viewModel = koinViewModel<LessonViewModel>(),
                    )
                }
            }
            dialog<SplashRoute>(
                dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                SplashScreen(
                    viewModel = koinViewModel<SplashViewModel>(),
                    navController = navController,
                )
            }

        }
    }
}