package ui.screenmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.myapplication.Database
import di.diContainer
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import models.Headline
import org.kodein.di.DI
import org.kodein.di.instance
import org.kodein.di.newInstance
import repo.NewsRepositoryRemoteImpl
import util.CustomException

class HomeScreenModel : ScreenModel {

    private val newsRepository by diContainer.newInstance {
        NewsRepositoryRemoteImpl(
            instance(),
            instance(tag = "apiKey")
        )
    }

    private val database: Database by diContainer.instance()

    data class UiState(
        val headlines: List<Headline> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchHeadlines()
    }

    fun fetchHeadlines() {
        coroutineScope.launch {

            updateHeadlines(database.getAllHeadlines())

            try {
                val headlines = newsRepository.fetchHeadlines(countryCode = "us", pageSize = 20)
                if (headlines.isNotEmpty()) {
                    updateHeadlines(headlines)
                    database.clearDatabase()
                    database.insertHeadlines(headlines)
                }
            } catch (e: Throwable) {
                val message = when(e){
                    is IOException -> "No internet connection"
                    else -> e.message.orEmpty()
                }
                updateError(message)
            }

        }
    }

    private fun updateError(message: String) {
        _uiState.update { it.copy(error = message) }
    }

    private fun updateHeadlines(headlines: List<Headline>) {
        _uiState.update { it.copy(headlines = headlines) }
    }
}