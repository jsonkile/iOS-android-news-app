package screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import models.Headline
import ui.components.HeaderCard
import ui.components.HeadlineCard
import ui.screens.HomeScreen

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@Preview
@Composable
fun PreviewHeaderCard() {
    HeaderCard(headline = Headline(title = "Headline card for the masses")){

    }
}

@Preview
@Composable
fun PreviewHeadlineCard() {
    HeadlineCard(
        headline = Headline(
            title = "Headline card for the masses",
            source = Headline.Source(name = "Channels TV")
        )
    ){

    }
}