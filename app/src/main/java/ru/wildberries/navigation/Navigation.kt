package ru.wildberries.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.domain.CommunityModel
import ru.wildberries.domain.EventModel
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.organism.BottomBar
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.screen.CommunitiesScreen
import ru.wildberries.ui.screen.CommunityDetailScreen
import ru.wildberries.ui.screen.EventDetailScreen
import ru.wildberries.ui.screen.EventsScreen
import ru.wildberries.ui.screen.FirstLessonScreen
import ru.wildberries.ui.screen.MoreScreen
import ru.wildberries.ui.screen.MyEventsScreen
import ru.wildberries.ui.screen.ProfileAccountScreen
import ru.wildberries.ui.screen.SecondLessonScreen
import ru.wildberries.ui.screen.SplashScreen

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
                startDestination = SplashRoute,
            ){
                composable<ProfileAccountRoute> { backStackEntry ->
                    ProfileAccountScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable<MoreRoute> { navBackStackEntry ->
                    MoreScreen(
                        viewModel = viewModel,
                        navigateToProfile = {navController.navigate(route = ProfileAccountRoute)},
                        navigateToFirstLesson = {navController.navigate(FirstLessonRoute)},
                        navigateToSecondLesson = {navController.navigate(SecondLessonRoute)},
                        navigateToMyEvents = {navController.navigate(MyEventsRoute)},
                    )
                }
                composable<FirstLessonRoute> {
                    FirstLessonScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable<SecondLessonRoute> {
                    SecondLessonScreen(
                        viewModel = viewModel,
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable<MyEventsRoute> {
                    MyEventsScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }
                composable<CommunitiesRoute> {
                    CommunitiesScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }
                composable<EventsRoute> {
                    EventsScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }
                composable<CommunityModel> { backStackEntry ->
                    val community: CommunityModel = backStackEntry.toRoute()
                    CommunityDetailScreen(
                        viewModel = viewModel,
                        community = community,
                        navController = navController
                    )
                }
                composable<EventModel> { backStackEntry ->
                    val event: EventModel = backStackEntry.toRoute()
                    EventDetailScreen(
                        viewModel = viewModel,
                        event = event,
                        navController = navController
                    )
                }
                dialog<SplashRoute>(
                    dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    SplashScreen(
                        navController = navController,
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

@Serializable
object SplashRoute

data class BottomNavigation(
    val eventsNavigate: () -> Unit,
    val communityNavigate: () -> Unit,
    val moreNavigate: () -> Unit
)