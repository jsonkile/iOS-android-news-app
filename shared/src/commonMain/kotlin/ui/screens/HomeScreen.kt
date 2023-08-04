package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import models.Headline
import ui.components.HeaderCard
import ui.components.HeadlineCard
import ui.screenmodels.HomeScreenModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { HomeScreenModel() }
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(items = uiState.headlines) { index: Int, item: Headline ->
                if (index == 0) {
                    HeaderCard(item) {
                        navigator.push(HeadlineScreen(headline = item, onBackClick = {
                            navigator.pop()
                        }))
                    }
                } else {
                    HeadlineCard(item) {
                        navigator.push(HeadlineScreen(headline = item, onBackClick = {
                            navigator.pop()
                        }))
                    }
                }
            }
        }
    }

}


