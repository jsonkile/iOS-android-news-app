package util

import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default

val customKamelConfig = KamelConfig {
    // Copies the default implementation if needed
    takeFrom(KamelConfig.Default)

    // Sets the number of images to cache
    imageBitmapCacheSize = 200

    this.httpFetcher {

    }
}