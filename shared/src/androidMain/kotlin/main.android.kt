import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Android"

actual fun openLinkInBrowser(link: String){
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    
}

@Composable fun MainView() = App()
