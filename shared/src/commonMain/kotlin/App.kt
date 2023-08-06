import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cafe.adriel.voyager.navigator.Navigator
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig
import ui.screens.HomeScreen
import util.customKamelConfig

@Composable
fun App() {
    MaterialTheme {
        CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
            Navigator(HomeScreen())
        }
    }
}

expect fun getPlatformName(): String