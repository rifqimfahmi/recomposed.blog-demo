package blog.recomposed.demosrecomposed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import blog.recomposed.demosrecomposed.ui.screens.DiscordAvatarScreen
import blog.recomposed.demosrecomposed.ui.screens.MainScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
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
