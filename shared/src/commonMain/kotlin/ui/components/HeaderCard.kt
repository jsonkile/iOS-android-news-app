package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Headline

@Composable
fun HeaderCard(headline: Headline, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onClick() }) {
        KamelImage(
            resource = asyncPainterResource(headline.urlToImage.orEmpty()),
            contentDescription = "header image",
            modifier = Modifier.aspectRatio(3 / 2f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(headline.title.orEmpty(), maxLines = 3)
    }
}