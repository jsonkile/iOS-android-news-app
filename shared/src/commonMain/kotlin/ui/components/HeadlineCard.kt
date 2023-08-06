package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Headline


@Composable
fun HeadlineCard(headline: Headline, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        KamelImage(
            resource = asyncPainterResource(headline.urlToImage.orEmpty()),
            contentDescription = "headline image",
            modifier = Modifier.weight(1f).aspectRatio(3 / 2f).background(color = Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(3f)) {
            Text(headline.title.orEmpty(), maxLines = 2)
            Spacer(modifier = Modifier.height(5.dp))
            Text(headline.source?.name.orEmpty(), fontSize = 13.sp)
        }
    }
}