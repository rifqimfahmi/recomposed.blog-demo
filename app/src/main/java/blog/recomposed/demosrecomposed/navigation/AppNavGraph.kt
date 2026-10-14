package blog.recomposed.demosrecomposed.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import blog.recomposed.demosrecomposed.ui.screens.DiscordAvatarScreen
import blog.recomposed.demosrecomposed.ui.screens.MainScreen

private const val TransitionDurationMillis = 300

private val enterFromRight: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(durationMillis = TransitionDurationMillis, easing = FastOutSlowInEasing),
    )
}

private val exitToLeft: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(durationMillis = TransitionDurationMillis, easing = FastOutSlowInEasing),
    )
}

private val popEnterFromLeft: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(durationMillis = TransitionDurationMillis, easing = FastOutSlowInEasing),
    )
}

private val popExitToRight: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(durationMillis = TransitionDurationMillis, easing = EaseOut),
    )
}

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
        enterTransition = enterFromRight,
        exitTransition = exitToLeft,
        popEnterTransition = popEnterFromLeft,
        popExitTransition = popExitToRight,
    ) {
        composable(Screen.Home.route) {
            MainScreen(
                onScreenSelected = { route -> navController.navigate(route) }
            )
        }
        composable(Screen.DiscordAvatar.route) {
            DiscordAvatarScreen()
        }
    }
}
