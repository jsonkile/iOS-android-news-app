package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import getPlatformName
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Headline

data class HeadlineScreen(val headline: Headline, val onBackClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.padding(20.dp)) {

            Image(
                imageVector = when (getPlatformName()) {
                    "iOS" -> Icons.Default.ArrowBackIosNew
                    else -> Icons.Default.ArrowBack
                },
                contentDescription = "back button",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        onBackClick()
                    }
                    .padding(5.dp)

            )

            Spacer(modifier = Modifier.height(15.dp))

            KamelImage(
                resource = asyncPainterResource(headline.urlToImage.orEmpty()),
                contentDescription = "header image",
                modifier = Modifier.aspectRatio(3 / 2f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(headline.title.orEmpty(), fontSize = 17.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                headline.source?.name.orEmpty(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Text(headline.author ?: headline.publishedAt.orEmpty(), fontSize = 13.sp)

            Spacer(modifier = Modifier.height(15.dp))

            Text(headline.description.orEmpty(), fontSize = 13.sp)

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                },
                shape = RectangleShape,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Text("Continue reading")
            }
        }
    }

}