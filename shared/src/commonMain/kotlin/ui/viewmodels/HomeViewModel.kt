package ui.viewmodels

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.diContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import models.Headline
import org.kodein.di.instance
import org.kodein.di.newInstance
import repo.NewsRepositoryRemoteImpl
import util.CustomException

class HomeViewModel : ViewModel() {

    private val newsRepository by diContainer.newInstance {
        NewsRepositoryRemoteImpl(
            instance(),
            instance(tag = "apiKey")
        )
    }

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
        viewModelScope.launch {
            try {
                val headlines = newsRepository.fetchHeadlines(countryCode = "us", pageSize = 20)
                updateHeadlines(headlines)
            } catch (e: CustomException) {
                updateError(e.message)
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