import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ui.screens.HomeScreen
import ui.viewmodels.HomeViewModel

@Composable
fun App() {
    MaterialTheme {
        val homeViewModel = getViewModel(Unit, viewModelFactory { HomeViewModel() })
        val uiState by homeViewModel.uiState.collectAsState()
        HomeScreen(uiState.headlines)
    }
}

expect fun getPlatformName(): String