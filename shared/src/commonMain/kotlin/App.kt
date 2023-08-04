import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ui.screens.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(HomeScreen())
    }
}

expect fun getPlatformName(): String

expect fun openLinkInBrowser(link: String)