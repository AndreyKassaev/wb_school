package ru.wildberries.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.organism.BottomBar
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.screen.CommunitiesScreen
import ru.wildberries.ui.screen.EventsScreen
import ru.wildberries.ui.screen.FirstLessonScreen
import ru.wildberries.ui.screen.MoreScreen
import ru.wildberries.ui.screen.MyEventsScreen
import ru.wildberries.ui.screen.ProfileAccountScreen
import ru.wildberries.ui.screen.SecondLessonScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val viewModel = MainViewModel(MockRepositoryImpl())

    Scaffold(
        topBar = {
            TopBar(viewModel = viewModel)
        },
        bottomBar = {
            BottomBar(
                viewModel = viewModel,
                bottomNavigation = BottomNavigation(
                    eventsNavigate = { navController.navigate(EventsRoute) },
                    communityNavigate = { navController.navigate(CommunitiesRoute) },
                    moreNavigate = { navController.navigate( MoreRoute ) }
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ){
            NavHost(
                navController = navController,
                startDestination = ProfileAccountRoute,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ){
                composable<ProfileAccountRoute> { backStackEntry ->
                    ProfileAccountScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.navigate(MoreRoute)}
                    )
                }
                composable<MoreRoute> { navBackStackEntry ->
                    MoreScreen(
                        viewModel = viewModel,
                        navigateToProfile = {navController.popBackStack(route = ProfileAccountRoute, inclusive = false)},
                        navigateToFirstLesson = {navController.navigate(FirstLessonRoute)},
                        navigateToSecondLesson = {navController.navigate(SecondLessonRoute)},
                        navigateToMyEvents = {navController.navigate(MyEventsRoute)},
                    )
                }
                composable<FirstLessonRoute> {
                    FirstLessonScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.popBackStack(route = MoreRoute, inclusive = false)}
                    )
                }
                composable<SecondLessonRoute> {
                    SecondLessonScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.popBackStack(route = MoreRoute, inclusive = false)}
                    )
                }
                composable<MyEventsRoute> {
                    MyEventsScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.popBackStack(route = MoreRoute, inclusive = false)}
                    )
                }
                composable<CommunitiesRoute> {
                    CommunitiesScreen(
                        viewModel = viewModel
                    )
                }
                composable<EventsRoute> {
                    EventsScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Serializable
object ProfileAccountRoute

@Serializable
object MoreRoute

@Serializable
object FirstLessonRoute

@Serializable
object SecondLessonRoute

@Serializable
object MyEventsRoute

@Serializable
object EventsRoute

@Serializable
object CommunitiesRoute

data class BottomNavigation(
    val eventsNavigate: () -> Unit,
    val communityNavigate: () -> Unit,
    val moreNavigate: () -> Unit
)