package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Headline

@Composable
fun HomeScreen(headlines: List<Headline> = emptyList()) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        itemsIndexed(items = headlines) { index: Int, item: Headline ->
            if (index == 0) {
                HeaderCard(item)
            } else {
                HeadlineCard(item)
            }
        }
    }
}

@Composable
fun HeaderCard(headline: Headline) {
    Column(modifier = Modifier) {
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

@Composable
fun HeadlineCard(headline: Headline) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        KamelImage(
            resource = asyncPainterResource(headline.urlToImage.orEmpty()),
            contentDescription = "headline image",
            modifier = Modifier.weight(1f).aspectRatio(3 / 2f),
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

