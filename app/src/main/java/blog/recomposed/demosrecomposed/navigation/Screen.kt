package blog.recomposed.demosrecomposed.navigation

sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("home", "Home")
    data object DiscordAvatar : Screen("discord_avatar", "Discord Avatar")
}

val destinationScreens = listOf(
    Screen.DiscordAvatar,
)
